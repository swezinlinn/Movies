package com.android.movies.ui

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.android.movies.domain.model.MovieList
import com.android.movies.util.Constants
import com.android.movies.presentation.viewModel.MovieListViewModel
import com.android.movies.R
import com.android.movies.databinding.FragmentMovieListBinding
import com.android.movies.presentation.adapter.MovieItemAdapter
import com.android.movies.presentation.adapter.MovieLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list), MovieItemAdapter.OnItemClickListener,SearchView.OnQueryTextListener {

    private lateinit var _binding: FragmentMovieListBinding
    private val binding get() = _binding
    private val viewModel: MovieListViewModel by viewModels()
    private var searchMenuItem: MenuItem? = null
    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MovieItemAdapter(this,R.drawable.ic_place_holder,requireContext())

        _binding = FragmentMovieListBinding.bind(view)
        binding.apply {
            movieRecyclerView.setHasFixedSize(true)
            movieRecyclerView.itemAnimator = null
            movieRecyclerView.adapter = adapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { adapter.retry() }
            )
        }

        setHasOptionsMenu(true)
        setUpToolbar(binding.toolbar)

        lifecycleScope.launch {
            viewModel.movieList.observe(viewLifecycleOwner) {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }


        adapter.addLoadStateListener { loadState ->
            binding.apply {
                movieProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                movieRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    movieRecyclerView.isVisible = false
                    textViewError.isVisible = true
                } else {
                    textViewError.isVisible = false
                }
            }
        }

    }

    private fun setUpToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val inflater = requireActivity().menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        searchMenuItem = menu.findItem(R.id.search)
        searchView = searchMenuItem?.actionView as SearchView

        val searchIcon: ImageView = searchView.findViewById(R.id.search_button)
        searchIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        val searchCloseIcon: ImageView = searchView.findViewById(R.id.search_close_btn)
        searchCloseIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_cross))
        val searchText: EditText = searchView.findViewById(R.id.search_src_text)
        searchText.setTextColor(resources.getColor(R.color.colorTextPrimary))
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search..."
        searchView.maxWidth = Int.MAX_VALUE
    }

    override fun onItemClick(movie: MovieList) {
        val bundle : Bundle = Bundle()
        bundle.putString("movieId",movie.imdbID)
        findNavController().navigate(R.id.movie_detail_fragment,bundle)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            binding.movieRecyclerView.scrollToPosition(0)
            if(newText.length < 2){
                viewModel.searchPhotos(Constants.DEFAULT_QUERY)
            }else {
                viewModel.searchPhotos(newText)
            }
        }
        return true
    }
}
package com.android.movies.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.movies.R
import com.android.movies.databinding.FragmentMovieDetailBinding
import com.android.movies.databinding.FragmentMovieListBinding
import com.android.movies.presentation.viewModel.MovieDetailViewModel
import com.android.movies.presentation.viewModel.MovieListViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail){
    private lateinit var _binding: FragmentMovieDetailBinding
    private val binding get() = _binding
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)

        binding.movieDetailViewModel = viewModel
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        val movieId = safeArgs.movieId

        setUpToolbar(binding.detailToolbar)
        setHasOptionsMenu(true)

        viewModel.loadingState.observe(viewLifecycleOwner,{
            if(it){
                binding.loading.visibility = View.VISIBLE
            }else{
                binding.loading.visibility = View.GONE
            }
        })

        viewModel.movieData.observe(viewLifecycleOwner, Observer {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_place_holder)
            requestOptions.error(R.drawable.ic_place_holder)
            Glide.with(requireContext())
                .load(it.poster)
                .apply(requestOptions)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.detailToolbarImageView)
            binding.apply {
                genure.text = it.genure
                detailToolbar.title = it.title
                rated.text = it.rated
                released.text = it.year
                rating.text = it.imdbRating
                plot.text = it.plot
                director.text = it.director
                writer.text = it.writer
                actor.text = it.actors
            }

        })

        viewModel.getMovieDetail(movieId)
    }

    private fun setUpToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
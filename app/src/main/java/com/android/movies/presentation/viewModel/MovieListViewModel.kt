package com.android.movies.presentation.viewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.android.movies.util.Constants
import com.android.movies.domain.repo.MovieRepository
import com.android.movies.presentation.BaseViewModel
import com.android.movies.util.decode

class MovieListViewModel @ViewModelInject constructor(@Assisted state: SavedStateHandle,
                                                      private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val TAG = MovieListViewModel::class.java.simpleName
    private val currentQuery = state.getLiveData(Constants.CURRENT_QUERY, Constants.DEFAULT_QUERY)

    val movieList = currentQuery.switchMap { queryString ->
        viewModelScope.let {
            movieRepository.getMovieList(decode(Constants.KEY),queryString,"movie").cachedIn(viewModelScope)
        }
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

}
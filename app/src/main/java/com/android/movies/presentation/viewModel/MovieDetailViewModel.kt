package com.android.movies.presentation.viewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.android.movies.domain.model.MovieDetail
import com.android.movies.domain.usecase.GetMovieDetail
import com.android.movies.presentation.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MovieDetailViewModel @ViewModelInject constructor(private val getMovieDetail: GetMovieDetail) : BaseViewModel() {

    private val TAG = MovieDetailViewModel::class.java.name
    val movieData = MutableLiveData<MovieDetail>()
    val loadingState = MutableLiveData<Boolean>()


    fun getMovieDetail(movieId: String) {
        loadingState.value = true
        getMovieDetail.execute(movieId).subscribeBy(
            onSuccess = { loadingState.value = false
                        movieData.value = it},
            onError = { loadingState.value = false }
        ).addTo(compositeDisposal)
    }
}
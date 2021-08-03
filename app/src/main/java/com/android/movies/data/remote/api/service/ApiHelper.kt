package com.android.movie.data.remote.api.service

import com.android.movie.data.model.MovieDetailResponse
import com.android.movies.data.model.MovieListResponse
import io.reactivex.Single

interface ApiHelper {

    fun getMovieDetail(movieId: String): Single<MovieDetailResponse>
}
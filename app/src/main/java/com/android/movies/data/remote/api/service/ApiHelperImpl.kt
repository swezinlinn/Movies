package com.android.movies.data.remote.api.service

import com.android.movies.data.remote.api.MovieApi
import com.android.movie.data.model.MovieDetailResponse
import com.android.movie.data.remote.api.service.ApiHelper
import com.android.movies.util.Constants
import com.android.movies.util.decode
import io.reactivex.Single

class ApiHelperImpl(
    private val movieApi: MovieApi
) : ApiHelper {

    override fun getMovieDetail(movieId: String): Single<MovieDetailResponse> {
        return movieApi.getMovieDetail(decode(Constants.KEY),movieId)
    }
}
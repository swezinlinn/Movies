package com.android.movies.data.repository

import com.android.movies.data.mapper.MovieResponseMapper
import com.android.movie.data.remote.api.service.ApiHelper
import com.android.movies.domain.model.MovieDetail
import io.reactivex.Single

class MovieRepoImpl(private val apiHelper: ApiHelper) : MovieRepo {
    override fun getMovieDetail(movieId: String): Single<MovieDetail> {
        return apiHelper.getMovieDetail(movieId).map { MovieResponseMapper.mapMovieDetailResponseToMovieDetail(it) }
    }
}
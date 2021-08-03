package com.android.movies.data.repository

import com.android.movies.domain.model.MovieDetail
import io.reactivex.Single

interface MovieRepo {
    fun getMovieDetail(movieId: String): Single<MovieDetail>
}
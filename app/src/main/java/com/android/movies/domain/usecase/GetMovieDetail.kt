package com.android.movies.domain.usecase

import com.android.movies.domain.model.MovieDetail
import com.android.movies.data.repository.MovieRepo
import com.android.movie.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetail @Inject constructor(private val movieRepo: MovieRepo) : SingleUseCase<MovieDetail,String>() {

    override fun provideSingle(params: String): Single<MovieDetail> {
        return movieRepo.getMovieDetail(params)
    }
}
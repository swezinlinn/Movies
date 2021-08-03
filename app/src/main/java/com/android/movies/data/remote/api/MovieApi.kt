package com.android.movies.data.remote.api

import com.android.movie.data.model.MovieDetailResponse
import com.android.movies.data.model.MovieListResponse
import com.android.movies.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(BuildConfig.BASE_URL)
    suspend fun getMovieList(
        @Query("apikey") apiKey : String,
        @Query("s") searchKey : String,
        @Query("type") type : String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ) : MovieListResponse

    @GET(BuildConfig.BASE_URL)
    fun getMovieDetail(
        @Query("apikey") apiKey : String,
        @Query("i") movieId : String,
    ) : Single<MovieDetailResponse>
}
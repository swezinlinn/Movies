package com.android.movies.domain.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.android.movies.data.remote.db.dao.MovieDao
import com.android.movies.data.paging.MovieDataSource
import com.android.movies.data.remote.api.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi,private val dao: MovieDao) {

    fun getMovieList(apiKey: String,status: String, type: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieDataSource(movieApi,apiKey,status,type,dao) }
        ).liveData


}
package com.android.movies.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.movies.data.mapper.MovieResponseMapper
import com.android.movies.data.remote.db.dao.MovieDao
import com.android.movies.domain.model.MovieList
import com.android.movies.data.remote.api.MovieApi
import com.bumptech.glide.load.HttpException
import java.io.IOException
private const val STARTING_PAGE_INDEX = 1

class MovieDataSource(
    private val movieApi: MovieApi,
    private val apiKey: String,
    private val searchKey: String,
    private val type: String,
    private val dao: MovieDao
) : PagingSource<Int, MovieList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieList> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            var photos = emptyList<MovieList>()
            val movieTable = MovieResponseMapper.mapMovieEntityToMovieList(dao.getSearchedMovieList(searchKey))
            photos = if(movieTable.isNotEmpty()) {
                movieTable
            }else {
                val response = MovieResponseMapper.mapMovieListResponseToMovieList(
                    movieApi.getMovieList(
                        apiKey,
                        searchKey,
                        type,
                        position,
                        params.loadSize
                    ).search
                )
                dao.insertAll(MovieResponseMapper.mapMovieListToMovieEntity(response))
                response
            }

            LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieList>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
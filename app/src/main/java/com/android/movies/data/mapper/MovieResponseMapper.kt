package com.android.movies.data.mapper

import com.android.movie.data.model.MovieDetailResponse
import com.android.movies.data.model.MovieListResponse
import com.android.movies.domain.model.MovieDetail
import com.android.movies.data.remote.db.entity.MovieEntity
import com.android.movies.domain.model.MovieList

class MovieResponseMapper {
    companion object {
        val mapMovieDetailResponseToMovieDetail: ((MovieDetailResponse) -> (MovieDetail)) = {
            MovieDetail(
                it.title ?: "",
                it.year ?: "",
                it.rated ?: "",
                it.runtime ?: "",
                it.director ?: "",
                it.writer ?: "",
                it.actors ?: "",
                it.plot ?: "",
                it.awards ?: "",
                it.poster ?: "",
                it.imdbRating ?: "",
                it.imdbVotes ?: "",
                it.imdbID ?: "",
                it.production ?: "",
                it.genre?:""
            )
        }

        val mapMovieListResponseToMovieList: ((List<MovieListResponse.Search?>?) -> (List<MovieList>)) =
            {
                val tempList = mutableListOf<MovieList>()
                if (it != null) {
                    for (value in it) {
                        if (value != null) {
                            tempList.add(
                                MovieList(
                                    value.title ?: "",
                                    value.year ?: "",
                                    value.imdbID ?: "",
                                    value.type ?: "",
                                    value.poster ?: ""
                                )
                            )
                        } else {
                            tempList.add(MovieList())
                        }
                    }
                }

                tempList
            }


        val mapMovieEntityToMovieList: ((List<MovieEntity?>?) -> (List<MovieList>)) =
            {
                val tempList = mutableListOf<MovieList>()
                if (it != null) {
                    for (value in it) {
                        if (value != null) {
                            tempList.add(
                                MovieList(
                                    value.title ?: "",
                                    value.year ?: "",
                                    value.imdbID ?: "",
                                    value.type ?: "",
                                    value.poster ?: ""
                                )
                            )
                        } else {
                            tempList.add(MovieList())
                        }
                    }
                }

                tempList
            }

        val mapMovieListToMovieEntity: ((List<MovieList?>?) -> (List<MovieEntity>)) =
            {
                val tempList = mutableListOf<MovieEntity>()
                if (it != null) {
                    for (value in it) {
                        if (value != null) {
                            tempList.add(
                                MovieEntity(
                                    title = value.title ?: "",
                                    year = value.year ?: "",
                                    imdbID = value.imdbID ?: "",
                                    type = value.type ?: "",
                                    poster = value.poster ?: ""
                                )
                            )
                        } else {
                            tempList.add(MovieEntity())
                        }
                    }
                }

                tempList
            }
    }
}
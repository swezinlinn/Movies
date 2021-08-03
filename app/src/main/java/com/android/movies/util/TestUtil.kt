package com.android.movie.util

import com.android.movies.data.remote.db.entity.MovieEntity
import com.android.movies.domain.model.MovieDetail
import com.android.movies.domain.model.MovieList

object TestUtil {

    fun createMovieList(id: Int): List<MovieEntity> {
    val tempList = mutableListOf<MovieEntity>()
    tempList.add(MovieEntity( id,"Aventure malgache",
    "1944",
    "tt0036621",
    "movie",
    "https://m.media-amazon.com/images/M/MV5BMTUyMTk4NTM2MV5BMl5BanBnXkFtZTcwOTYwMDUzMQ@@._V1_SX300.jpg"
    ))
    return tempList
    }

    fun createMovieDetail(id: String): MovieDetail{
        return MovieDetail(
            "Captain Marvel",
            "2019",
            "PG-13",
            "123 min",
            "Anna Boden, Ryan Fleck",
            "Anna Boden, Ryan Fleck, Geneva Robertson-Dworet",
            "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.",
            "9 wins & 52 nominations",
            "https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@._V1_SX300.jpg",
            "6.8",
            "477,939",
            id,
            "Marvel Studios",
            "Action, Adventure, Sci-Fi"
        )
    }
}
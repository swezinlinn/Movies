package com.android.movies.data.remote.db.dao


import androidx.room.*
import com.android.movies.data.remote.db.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movie: List<MovieEntity>)

    @Query("Select * from Movie Where title = :title")
    suspend fun getSearchedMovieList(title: String): List<MovieEntity>


}
package com.android.movies.data.remote.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String = "",
    var year: String = "",
    var imdbID: String = "",
    var type: String = "",
    var poster: String = "")
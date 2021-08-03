package com.android.movies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MovieListResponse{
    @SerializedName("Search")
    @Expose
    val search: List<Search?>? = null

    @SerializedName("totalResults")
    @Expose
    val totalResults: String? = null

    @SerializedName("Response")
    @Expose
    val response: String? = null

    class Search {
        @SerializedName("Title")
        @Expose
        val title: String? = null

        @SerializedName("Year")
        @Expose
        val year: String? = null

        @SerializedName("imdbID")
        @Expose
        val imdbID: String? = null

        @SerializedName("Type")
        @Expose
        val type: String? = null

        @SerializedName("Poster")
        @Expose
        val poster: String? = null
    }
}
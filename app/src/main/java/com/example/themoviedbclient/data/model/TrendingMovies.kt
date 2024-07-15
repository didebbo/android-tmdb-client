package com.example.themoviedbclient.data.model

import com.google.gson.annotations.SerializedName

data class TrendingMovies(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResult: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val  result: List<Movie>
)

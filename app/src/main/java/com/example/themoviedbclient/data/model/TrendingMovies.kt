package com.example.themoviedbclient.data.model

import com.google.gson.annotations.SerializedName

data class TrendingMovies(
    @SerializedName("total_pages")
    private val totalPages: Int,
    @SerializedName("total_results")
    private val totalResult: Int,
    @SerializedName("page")
    private val page: Int,
    @SerializedName("results")
    private  val  result: ArrayList<Movie>
)

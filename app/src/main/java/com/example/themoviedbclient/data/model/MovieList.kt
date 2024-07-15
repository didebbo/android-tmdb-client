package com.example.themoviedbclient.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page")
    private val page: Int,

    @SerializedName("results")
    private  val  result: ArrayList<Movie>
)

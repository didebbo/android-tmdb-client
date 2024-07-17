package com.example.themoviedbclient.data.model.movie

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("results")
    val  result: List<Movie>
)
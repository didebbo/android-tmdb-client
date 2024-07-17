package com.example.themoviedbclient.data.dto.movie

import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("results")
    val  result: List<MovieDTO>
)
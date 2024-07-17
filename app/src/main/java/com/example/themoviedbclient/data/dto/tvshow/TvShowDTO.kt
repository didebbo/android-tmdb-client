package com.example.themoviedbclient.data.dto.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val coverPath: String
)
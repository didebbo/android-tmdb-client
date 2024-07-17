package com.example.themoviedbclient.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String
)

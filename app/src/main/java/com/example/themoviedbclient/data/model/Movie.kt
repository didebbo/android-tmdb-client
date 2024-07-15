package com.example.themoviedbclient.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    private val id: Int,

    @SerializedName("title")
    private val title: String,

    @SerializedName("overview")
    private val overview: String,

    @SerializedName("poster_path")
    private val posterPath: String,

    @SerializedName("release_date")
    private val releaseDate: String,

    @SerializedName("popularity")
    private val popularity: Double,

    @SerializedName("video")
    private val video: Boolean,

    @SerializedName("vote_average")
    private  val voteAverage: Double,

    @SerializedName("vote_count")
    private val voteCount: Int
)

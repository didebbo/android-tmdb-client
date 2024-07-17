package com.example.themoviedbclient.data.model.tvshow

import com.example.themoviedbclient.data.model.movie.Movie
import com.google.gson.annotations.SerializedName

data class TvShows(
    @SerializedName("results")
    val  result: List<TvShow>
)
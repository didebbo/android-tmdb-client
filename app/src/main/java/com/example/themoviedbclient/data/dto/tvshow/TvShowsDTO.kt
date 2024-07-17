package com.example.themoviedbclient.data.dto.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowsDTO(
    @SerializedName("results")
    val  result: List<TvShowDTO>
)
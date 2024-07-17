package com.example.themoviedbclient.data.model

data class ItemModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val coverPath: String,
    var saved: Boolean
)

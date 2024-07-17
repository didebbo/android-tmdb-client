package com.example.themoviedbclient.data.model

import java.net.URL

data class ItemModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterURL: String,
    val coverURL: String
)

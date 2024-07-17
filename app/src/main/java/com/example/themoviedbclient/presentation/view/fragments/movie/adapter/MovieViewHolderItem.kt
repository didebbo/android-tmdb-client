package com.example.themoviedbclient.presentation.view.fragments.movie.adapter

import com.example.themoviedbclient.presentation.baseclass.util.BaseViewHolderItem

class MovieViewHolderItem(
    val fullPosterPath: String,
    val title: String,
    val overview: String
): BaseViewHolderItem()
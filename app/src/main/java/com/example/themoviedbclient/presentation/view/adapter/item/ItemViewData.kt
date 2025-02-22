package com.example.themoviedbclient.presentation.view.adapter.item

import com.example.themoviedbclient.presentation.baseclass.adapter.BaseViewHolderItem

class ItemViewData(
    val title: String,
    val overview: String,
    val posterPath: String,
    val coverPath: String,
    val saved: Boolean,
    val fromRemote: Boolean,
    val onDetail: () -> Unit,
    val onSave: (() -> Unit)? = null,
    val onDelete: (() -> Unit)? = null
): BaseViewHolderItem()
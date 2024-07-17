package com.example.themoviedbclient.presentation.baseclass.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<V: View, I: BaseViewHolderItem>(itemView: V): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: I)
}
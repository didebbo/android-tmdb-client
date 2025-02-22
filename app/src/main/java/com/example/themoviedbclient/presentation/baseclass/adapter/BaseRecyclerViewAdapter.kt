package com.example.themoviedbclient.presentation.baseclass.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<VH: BaseViewHolder<View, I>, I: BaseViewHolderItem>(private val data: List<I>): RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return createViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutFrom(position)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

    abstract fun createViewHolder(view: View): VH
    abstract fun getLayoutFrom(position: Int): Int
}
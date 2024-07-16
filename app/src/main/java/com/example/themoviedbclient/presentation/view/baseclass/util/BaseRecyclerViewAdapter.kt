package com.example.themoviedbclient.presentation.view.baseclass.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedbclient.R

abstract class BaseRecyclerViewAdapter<VH: BaseViewHolder<View,I>, I: BaseViewHolderItem>(private val data: List<I>): RecyclerView.Adapter<VH>() {
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
    open fun getLayoutFrom(position: Int): Int {
        return R.layout.empty_recycler_item_view
    }
}
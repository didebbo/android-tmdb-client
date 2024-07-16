package com.example.themoviedbclient.presentation.view.baseclass.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedbclient.R

abstract class BaseRecyclerViewAdapter<V: BaseViewHolder<View,D>, D: BaseViewHolderItem>(private val data: List<D>): RecyclerView.Adapter<V>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        Log.i("[GN]","onCreateViewHolder")
        val layoutId = getLayoutId(viewType)
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return createViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.i("[GN]","Size: ${data.size}")
        return data.size
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        Log.i("[GN]","position: ${position.toString()}")
        holder.bind(data[position])
    }

    abstract fun createViewHolder(view: View): V
    open fun getLayoutId(viewType: Int): Int {
        return R.layout.empty_recycler_item_view
    }
}
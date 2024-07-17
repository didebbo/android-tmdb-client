package com.example.themoviedbclient.presentation.view.adapter.item

import android.view.View
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseRecyclerViewAdapter

class ItemViewAdapter(data: List<ItemViewData>): BaseRecyclerViewAdapter<ItemViewHolder, ItemViewData>(data){

    override fun createViewHolder(view: View): ItemViewHolder {
        return ItemViewHolder.SmallItemViewHolder(view)
    }

    override fun getLayoutFrom(position: Int): Int {
        return R.layout.small_item_recycler_view
    }
}
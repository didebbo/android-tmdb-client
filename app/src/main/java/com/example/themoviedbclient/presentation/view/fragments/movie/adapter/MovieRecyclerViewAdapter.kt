package com.example.themoviedbclient.presentation.view.fragments.movie.adapter

import android.view.View
import com.example.themoviedbclient.R
import com.example.themoviedbclient.presentation.baseclass.util.BaseRecyclerViewAdapter

class MovieRecyclerViewAdapter(data: List<MovieViewHolderItem>): BaseRecyclerViewAdapter<MovieViewHolder, MovieViewHolderItem>(data){

    override fun createViewHolder(view: View): MovieViewHolder {
        return MovieViewHolder.SmallItemMovieViewHolder(view)
    }

    override fun getLayoutFrom(position: Int): Int {
        return R.layout.small_item_recycler_view
    }
}
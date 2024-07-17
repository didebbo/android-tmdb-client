package com.example.themoviedbclient.presentation.view.fragments.movie

import android.view.View
import com.bumptech.glide.Glide
import com.example.themoviedbclient.R
import com.example.themoviedbclient.databinding.SmallItemRecyclerViewBinding
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolder
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolderItem

class MovieViewHolderItem(
    val fullPosterPath: String,
    val title: String,
    val overview: String
): BaseViewHolderItem()

sealed class MovieViewHolder(itemView: View): BaseViewHolder<View,MovieViewHolderItem>(itemView)  {
    class SmallItemMovieViewHolder(itemView: View): MovieViewHolder(itemView) {

        private val binding: SmallItemRecyclerViewBinding = SmallItemRecyclerViewBinding.bind(itemView)
        override fun bind(item: MovieViewHolderItem) {
            binding.titleTextView.text = item.title
            binding.overviewTextView.text = item.overview
            Glide.with(binding.root).load(item.fullPosterPath).into(binding.posterImage)
        }
    }
}

class MovieRecyclerViewAdapter(data: List<MovieViewHolderItem>): BaseRecyclerViewAdapter<MovieViewHolder,MovieViewHolderItem>(data){

    override fun createViewHolder(view: View): MovieViewHolder {
        return MovieViewHolder.SmallItemMovieViewHolder(view)
    }

    override fun getLayoutFrom(position: Int): Int {
        return R.layout.small_item_recycler_view
    }
}
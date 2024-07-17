package com.example.themoviedbclient.presentation.view.fragments.trendingMovies

import android.view.View
import com.bumptech.glide.Glide
import com.example.themoviedbclient.R
import com.example.themoviedbclient.databinding.SmallItemRecyclerViewBinding
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolder
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolderItem

class TrendingMoviesItem(
    val fullPosterPath: String,
    val title: String,
    val overview: String
): BaseViewHolderItem()

sealed class TrendingMovieViewHolder(itemView: View): BaseViewHolder<View,TrendingMoviesItem>(itemView)  {
    class SmallItemTrendingMovieViewHolder(itemView: View): TrendingMovieViewHolder(itemView) {

        private val binding: SmallItemRecyclerViewBinding = SmallItemRecyclerViewBinding.bind(itemView)
        override fun bind(item: TrendingMoviesItem) {
            binding.titleTextView.text = item.title
            binding.overviewTextView.text = item.overview
            Glide.with(binding.root).load(item.fullPosterPath).into(binding.posterImage)
        }
    }
}

class TrendingMoviesAdapter(data: List<TrendingMoviesItem>): BaseRecyclerViewAdapter<TrendingMovieViewHolder,TrendingMoviesItem>(data){

    override fun createViewHolder(view: View): TrendingMovieViewHolder {
        return TrendingMovieViewHolder.SmallItemTrendingMovieViewHolder(view)
    }

    override fun getLayoutFrom(position: Int): Int {
        return R.layout.small_item_recycler_view
    }
}
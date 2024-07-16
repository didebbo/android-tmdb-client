package com.example.themoviedbclient.presentation.view.fragments.trendingMovies

import android.view.View
import com.bumptech.glide.Glide
import com.example.themoviedbclient.R
import com.example.themoviedbclient.databinding.TrendingMovieRecyclerItemViewBinding
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolder
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolderItem

class TrendingMoviesAdapter(data: List<TrendingMoviesItem>): BaseRecyclerViewAdapter<TrendingMoviesAdapter.TrendingMoviesViewHolder,TrendingMoviesAdapter.TrendingMoviesItem>(data){

    class TrendingMoviesItem(
        val fullPosterPath: String,
        val title: String,
        val overview: String
    ): BaseViewHolderItem()

    inner class TrendingMoviesViewHolder(itemView: View): BaseViewHolder<View,TrendingMoviesItem>(itemView) {

        private val binding: TrendingMovieRecyclerItemViewBinding = TrendingMovieRecyclerItemViewBinding.bind(itemView)
        override fun bind(item: TrendingMoviesItem) {
            binding.titleTextView.text = item.title
            binding.overviewTextView.text = item.overview
            Glide.with(binding.root).load(item.fullPosterPath).into(binding.posterImage)
        }
    }

    override fun createViewHolder(view: View): TrendingMoviesViewHolder {
        return TrendingMoviesViewHolder(view)
    }

    override fun getLayoutFrom(position: Int): Int {
        return R.layout.trending_movie_recycler_item_view
    }
}
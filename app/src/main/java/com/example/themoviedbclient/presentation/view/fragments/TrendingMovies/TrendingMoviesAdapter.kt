package com.example.themoviedbclient.presentation.view.fragments.TrendingMovies

import android.util.Log
import android.view.View
import com.example.themoviedbclient.R
import com.example.themoviedbclient.databinding.TrendingMovieRecyclerItemViewBinding
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolder
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolderItem

class TrendingMoviesAdapter(data: List<TrendingMoviesItem>): BaseRecyclerViewAdapter<TrendingMoviesAdapter.TrendingMoviesViewHolder,TrendingMoviesAdapter.TrendingMoviesItem>(data){

    class TrendingMoviesItem(
        val value: String
    ): BaseViewHolderItem()

    inner class TrendingMoviesViewHolder(itemView: View): BaseViewHolder<View,TrendingMoviesItem>(itemView) {

        private val binding: TrendingMovieRecyclerItemViewBinding = TrendingMovieRecyclerItemViewBinding.bind(itemView)
        override fun bind(item: TrendingMoviesItem) {
            Log.i("[GN]", item.value)
            binding.title.text = item.value
        }
    }

    override fun createViewHolder(view: View): TrendingMoviesViewHolder {
        return TrendingMoviesViewHolder(view)
    }

    override fun getLayoutId(viewType: Int): Int {
        Log.i("[GN]", "viewType: $viewType")
        return R.layout.trending_movie_recycler_item_view
    }
}
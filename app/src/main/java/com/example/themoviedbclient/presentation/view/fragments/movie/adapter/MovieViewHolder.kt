package com.example.themoviedbclient.presentation.view.fragments.movie.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.themoviedbclient.databinding.SmallItemRecyclerViewBinding
import com.example.themoviedbclient.presentation.baseclass.util.BaseViewHolder

sealed class MovieViewHolder(itemView: View): BaseViewHolder<View, MovieViewHolderItem>(itemView)  {
    class SmallItemMovieViewHolder(itemView: View): MovieViewHolder(itemView) {

        private val binding: SmallItemRecyclerViewBinding =
            SmallItemRecyclerViewBinding.bind(itemView)
        override fun bind(item: MovieViewHolderItem) {
            binding.titleTextView.text = item.title
            binding.overviewTextView.text = item.overview
            Glide.with(binding.root).load(item.fullPosterPath).into(binding.posterImage)
        }
    }
}
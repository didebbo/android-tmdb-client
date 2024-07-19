package com.example.themoviedbclient.presentation.view.adapter.item

import android.view.View
import com.bumptech.glide.Glide
import com.example.themoviedbclient.databinding.SmallItemRecyclerViewBinding
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseViewHolder

sealed class ItemViewHolder(itemView: View): BaseViewHolder<View, ItemViewData>(itemView)  {
    class SmallItemViewHolder(itemView: View): ItemViewHolder(itemView) {

        private val binding: SmallItemRecyclerViewBinding =
            SmallItemRecyclerViewBinding.bind(itemView)
        override fun bind(item: ItemViewData) {
            binding.titleTextView.text = item.title
            binding.overviewTextView.text = item.overview
            Glide.with(binding.root).load(item.posterPath).into(binding.posterImage)
            binding.detailButton.setOnClickListener {
                item.onDetail()
            }
            binding.saveButton.setOnClickListener {
                item.onSave?.invoke()
            }
            binding.deleteButton.setOnClickListener {
                item.onDelete?.invoke()
            }

            binding.saveButton.visibility = if(item.fromRemote && !item.saved) View.VISIBLE else View.GONE
            binding.deleteButton.visibility = if(!item.fromRemote && item.saved) View.VISIBLE else View.GONE
        }
    }
}
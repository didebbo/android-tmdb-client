package com.example.themoviedbclient.presentation.view.fragments.detail.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.themoviedbclient.databinding.ItemDetailFragmentLayoutBinding
import com.example.themoviedbclient.presentation.view.activity.MainActivity
import com.example.themoviedbclient.presentation.viewmodel.detail.item.DetailItemViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

class DetailItemFragment: Fragment() {

    private val viewModel: DetailItemViewModel by activityViewModels()

    private lateinit var binding: ItemDetailFragmentLayoutBinding

    private val parent: MainActivity? by lazy {
        activity as? MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemDetailFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = viewModel.getItem()
        if(item != null) {
            Glide.with(binding.root).load(item.coverURL).into(binding.coverImage)
            binding.titleTextView.text = item.title
            binding.overviewTextView.text = item.overview
        }
    }
}
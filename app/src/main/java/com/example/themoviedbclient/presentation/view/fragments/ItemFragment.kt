package com.example.themoviedbclient.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.themoviedbclient.databinding.ItemDetailFragmentLayoutBinding
import com.example.themoviedbclient.presentation.view.activity.MainActivity
import com.example.themoviedbclient.presentation.viewmodel.item.ItemViewModelInterface
import kotlinx.coroutines.launch

class ItemFragment: Fragment() {

    private var viewModel: ItemViewModelInterface? = null

    private lateinit var binding: ItemDetailFragmentLayoutBinding

    private val parent: MainActivity? by lazy {
        activity as? MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemDetailFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = when(arguments?.getString("type")) {
            "movie" -> {
                parent?.movieViewModel
            }
            "tvShow" -> {
                parent?.tvShowViewModel
            }
            else -> null
        }

        viewModel?.let {
            binding(it)
            setActions(it)
        }
    }

    private fun binding(viewModel: ItemViewModelInterface) {
        viewModel.item.observe(viewLifecycleOwner) {
            it?.let {
                Glide.with(binding.root).load(viewModel.getImageFullPath(it.coverPath)).into(binding.coverImage)
                binding.titleTextView.text = it.title
                binding.overviewTextView.text = it.overview
                binding.saveButton.visibility = if(it.saved) View.GONE else View.VISIBLE
            }
        }
        viewModel.loader.observe(viewLifecycleOwner) {
            parent?.showLoader(it)
        }
    }
    private fun setActions(viewModel: ItemViewModelInterface) {
        binding.saveButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.saveItem()
            }
        }
    }
}
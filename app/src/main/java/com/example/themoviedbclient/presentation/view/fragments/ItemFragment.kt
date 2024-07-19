package com.example.themoviedbclient.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.themoviedbclient.databinding.ItemDetailFragmentLayoutBinding
import com.example.themoviedbclient.presentation.view.activity.MainActivity
import com.example.themoviedbclient.presentation.viewmodel.item.ItemViewModelInterface
import com.example.themoviedbclient.presentation.viewmodel.item.MovieViewModel
import com.example.themoviedbclient.presentation.viewmodel.item.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class ItemFragment: Fragment() {

    private var viewModel: ItemViewModelInterface? = null

    private lateinit var binding: ItemDetailFragmentLayoutBinding

    private var title: String? = null

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
                val movieViewModel: MovieViewModel by activityViewModels()
                title = "Movie"
                movieViewModel
            }
            "tvShow" -> {
                val tvShowViewModel: TvShowViewModel by activityViewModels()
                title = "Tv Show"
                tvShowViewModel
            }
            else -> null
        }

        viewModel?.let {
            binding(it)
            setActions(it)
        }
    }

    override fun onResume() {
        super.onResume()
        parent?.supportActionBar?.title = title
    }

    private fun binding(viewModel: ItemViewModelInterface) {
        viewModel.item.observe(viewLifecycleOwner) {
            it?.let {
                Glide.with(binding.root).load(viewModel.getImageFullPath(it.coverPath)).into(binding.coverImage)
                binding.titleTextView.text = it.title
                binding.overviewTextView.text = it.overview
                binding.saveButton.visibility = if(it.fromRemote && !it.saved) View.VISIBLE else View.GONE
                binding.deleteButton.visibility = if(!it.fromRemote && it.saved) View.VISIBLE else View.GONE
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
        binding.deleteButton.setOnClickListener {
            lifecycleScope.launch{
                viewModel.deleteItem()
            }
        }
    }
}
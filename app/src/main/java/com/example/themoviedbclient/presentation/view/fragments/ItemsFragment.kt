package com.example.themoviedbclient.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewAdapter
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewData
import com.example.themoviedbclient.presentation.viewmodel.item.ItemViewModelInterface
import com.example.themoviedbclient.presentation.viewmodel.item.MovieViewModel
import com.example.themoviedbclient.presentation.viewmodel.item.TvShowViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.ItemsViewModelInterFace
import com.example.themoviedbclient.presentation.viewmodel.items.MoviesViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.TvShowsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class ItemsFragment: BaseFragmentList() {

    private var itemsViewModel: ItemsViewModelInterFace? = null
    private var itemViewModel: ItemViewModelInterface? = null

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        when(arguments?.getString("type")) {
            "movies" -> {
                itemsViewModel = activityViewModels<MoviesViewModel>().value
                itemViewModel = activityViewModels<MovieViewModel>().value
            }
            "tvShows" -> {
                itemsViewModel = activityViewModels<TvShowsViewModel>().value
                itemViewModel = activityViewModels<TvShowViewModel>().value
            }
        }

        itemsViewModel?.let { itemsViewModel ->
            getResource(itemsViewModel)
            itemViewModel?.let { itemViewModel ->
                binding(itemsViewModel,itemViewModel)
            }
        }
    }

    private fun getResource(itemsViewModel: ItemsViewModelInterFace) {
        lifecycleScope.launch {
            itemsViewModel.fetchItemsResource()
        }
    }
    private fun binding(itemsViewModel: ItemsViewModelInterFace, itemViewModel: ItemViewModelInterface) {
        itemsViewModel.loader.observe(viewLifecycleOwner) {
            parent?.showLoader(it)
        }

        itemsViewModel.itemsResource.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Error -> {
                    Snackbar.make(binding.root,"Error: ${it.message}",Snackbar.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    val data: List<ItemModel> = it.data.orEmpty()
                    val items: List<ItemViewData> = data.map { item ->
                        ItemViewData(
                            item.title,
                            item.overview,
                            itemViewModel.getImageFullPath(item.posterPath),
                            itemViewModel.getImageFullPath(item.coverPath),
                            item.saved,
                            onDetail = {
                                itemViewModel.setItem(item)
                                navController?.navigate(itemsViewModel.navigateToItemActionId())
                            },
                            onSave = {
                                lifecycleScope.launch {
                                    itemsViewModel.saveItem(item)
                                }
                            }
                        )
                    }
                    val adapter = ItemViewAdapter(items)
                    setAdapter(adapter)
                }
                else -> Unit
            }
        }
    }
}
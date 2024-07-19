package com.example.themoviedbclient.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
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
import com.example.themoviedbclient.presentation.viewmodel.items.SavedItemsViewModelInterface
import com.example.themoviedbclient.presentation.viewmodel.items.TvShowsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ItemsFragment: BaseFragmentList() {
    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        when(arguments?.getString("type")) {
            "movies" -> {
                val itemsViewModel: MoviesViewModel by activityViewModels()
                val itemViewModel: MovieViewModel by activityViewModels()
                configureForRemoteResource(itemsViewModel,itemViewModel)
            }
            "tvShows" -> {
                val itemsViewModel: TvShowsViewModel by activityViewModels()
                val itemViewModel: TvShowViewModel by activityViewModels()
                configureForRemoteResource(itemsViewModel,itemViewModel)
            }
            "savedMoview" -> {
                TODO()
            }
        }
    }

    private fun configureForRemoteResource(itemsViewModel: ItemsViewModelInterFace, itemViewModel: ItemViewModelInterface) {
        getRemoteResource(itemsViewModel)
        bindForRemoteItem(itemsViewModel,itemViewModel)
    }

    private fun configureForSavedItems() {
        TODO()
    }

    private fun getRemoteResource(itemsViewModel: ItemsViewModelInterFace) {
        lifecycleScope.launch {
            itemsViewModel.fetchItemsResource()
        }
    }

    private  fun getLocalItems(savedItemsViewModelInterface: SavedItemsViewModelInterface) {
        lifecycleScope.launch {
            TODO()
        }
    }
    private fun bindForRemoteItem(itemsViewModel: ItemsViewModelInterFace, itemViewModel: ItemViewModelInterface) {
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
                            item.fromRemote,
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
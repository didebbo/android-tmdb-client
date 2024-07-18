package com.example.themoviedbclient.presentation.view.fragments.items

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewAdapter
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewData
import com.example.themoviedbclient.presentation.viewmodel.item.ItemViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.ItemsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemsFragment: BaseFragmentList() {

    private var itemsViewModel: ItemsViewModel? = null
    private var itemViewModel: ItemViewModel? = null

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        when(arguments?.getString("type")) {
            "movies" -> {
                itemsViewModel = parent?.moviesViewModel
                itemViewModel = parent?.movieViewModel
            }
            "tvShows" -> {
                itemsViewModel = parent?.tvShowsViewModel
                itemViewModel = parent?.tvShowViewModel
            }
        }

        itemsViewModel?.let { itemsViewModel ->
            getResource(itemsViewModel)
            bindLoader(itemsViewModel)
            itemViewModel?.let { itemViewModel ->
                bindResource(itemsViewModel,itemViewModel)
            }
        }
    }

    private fun getResource(itemsViewModel: ItemsViewModel) {
        lifecycleScope.launch {
            itemsViewModel.fetchItemsResource()
        }
    }

    private fun bindLoader(itemsViewModel: ItemsViewModel) {
        itemsViewModel.loader.observe(this) {
            parent?.showLoader(it)
        }
    }
    private fun bindResource(itemsViewModel: ItemsViewModel, itemViewModel: ItemViewModel) {
        itemsViewModel.itemsResource.observe(this) {
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
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
import com.example.themoviedbclient.presentation.viewmodel.items.SavedItemsViewModelInterface
import com.example.themoviedbclient.presentation.viewmodel.items.SavedMoviesViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.SavedTvShowsViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.TvShowsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ItemsFragment: BaseFragmentList() {

    private var title: String? = null

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        when(arguments?.getString("type")) {
            "movies" -> {
                val itemsViewModel: MoviesViewModel by activityViewModels()
                val itemViewModel: MovieViewModel by activityViewModels()
                title = "Movies"
                configureForRemoteResource(itemsViewModel,itemViewModel)
            }
            "tvShows" -> {
                val itemsViewModel: TvShowsViewModel by activityViewModels()
                val itemViewModel: TvShowViewModel by activityViewModels()
                title = "Tv Shows"
                configureForRemoteResource(itemsViewModel,itemViewModel)
            }
            "savedMovies" -> {
                val savedItemsViewModel: SavedMoviesViewModel by activityViewModels()
                val itemViewModel: MovieViewModel by activityViewModels()
                title = "Saved Movies"
                configureForSavedItems(savedItemsViewModel,itemViewModel)
            }
            "savedTvShow" -> {
                val savedItemsViewModel: SavedTvShowsViewModel by activityViewModels()
                val itemViewModel: TvShowViewModel by activityViewModels()
                title = "Saved Tv Shows"
                configureForSavedItems(savedItemsViewModel,itemViewModel)
            }
        }
    }

    override fun afterOnResume() {
        parent?.supportActionBar?.title = title
    }

    private fun configureForRemoteResource(itemsViewModel: ItemsViewModelInterFace, itemViewModel: ItemViewModelInterface) {
        getRemoteResource(itemsViewModel)
        bindForRemoteItem(itemsViewModel,itemViewModel)
    }

    private fun configureForSavedItems(savedItemsViewModel: SavedItemsViewModelInterface, itemViewModel: ItemViewModelInterface) {
        getLocalItems(savedItemsViewModel)
        bindForLocalItems(savedItemsViewModel,itemViewModel)
    }

    private fun getRemoteResource(itemsViewModel: ItemsViewModelInterFace) {
        lifecycleScope.launch {
            itemsViewModel.fetchItemsResource()
        }
    }

    private  fun getLocalItems(savedItemsViewModel: SavedItemsViewModelInterface) {
        lifecycleScope.launch {
            lifecycleScope.launch {
                savedItemsViewModel.fetchSavedItems()
            }
        }
    }
    private fun bindForRemoteItem(itemsViewModel: ItemsViewModelInterFace, itemViewModel: ItemViewModelInterface) {
        itemsViewModel.loader.observe(viewLifecycleOwner) {
            parent?.showLoader(it)
        }

        itemsViewModel.itemsResource.observe(viewLifecycleOwner) { it ->
            when(it) {
                is Resource.Loading -> {
                    parent?.showLoader(true)
                }
                is Resource.Error -> {
                    parent?.showAlertView(it.message ?: "Undefined Error on loading data")
                    itemsViewModel.clearInvalidResource()
                }
                is Resource.Success -> {
                    parent?.hideModalSystem()
                    val data: List<ItemModel> = it.data.orEmpty()
                    setAdapterWithData(
                        data = data,
                        getImageFullPath = { str -> itemsViewModel.getImageFullPath(str) },
                        onDetail = { item ->
                            itemViewModel.setItem(item)
                            navController?.navigate(itemsViewModel.navigateToItemActionId())
                        },
                        onSave = { item ->
                            lifecycleScope.launch {
                                itemsViewModel.saveItem(item)
                            }
                        }
                    )
                }
                else -> Unit
            }
        }
    }

    private  fun bindForLocalItems(savedItemsViewModel: SavedItemsViewModelInterface, itemViewModel: ItemViewModelInterface) {
        savedItemsViewModel.loader.observe(viewLifecycleOwner) {
            parent?.showLoader(it)
        }

        savedItemsViewModel.items.observe(this) { data ->
            setAdapterWithData(
                data,
                getImageFullPath = { str ->
                    savedItemsViewModel.getImageFullPath(str)
                },
                onDetail = { item ->
                    itemViewModel.setItem(item)
                    savedItemsViewModel.navigateToItemIdWithArgs().let {
                        navController?.navigate(it.first,it.second)
                    }
                },
                onDelete = { item ->
                    lifecycleScope.launch {
                        savedItemsViewModel.deleteItem(item)
                    }
                }
            )
        }
    }

    private fun setAdapterWithData(
        data: List<ItemModel>,
        getImageFullPath: (String) -> String,
        onDetail: (ItemModel) -> Unit,
        onSave: ((ItemModel) -> Unit)? = null,
        onDelete: ((ItemModel) -> Unit)? = null
    ) {
        val items: List<ItemViewData> = data.map { item ->
            ItemViewData(
                item.title,
                item.overview,
                getImageFullPath(item.posterPath),
                getImageFullPath(item.coverPath),
                item.saved,
                item.fromRemote,
                { onDetail(item) },
                { onSave?.invoke(item) },
                { onDelete?.invoke(item) }
            )
        }
        val adapter = ItemViewAdapter(items)
        setAdapter(adapter)
    }
}
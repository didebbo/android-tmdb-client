package com.example.themoviedbclient.presentation.view.fragments.tvshow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewAdapter
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewData
import com.example.themoviedbclient.presentation.viewmodel.detail.item.DetailItemViewModel
import com.example.themoviedbclient.presentation.viewmodel.tvshow.TvShowsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowsFragment: BaseFragmentList() {

    private val viewModel: TvShowsViewModel by activityViewModels()
    private val detailItemViewModel: DetailItemViewModel by activityViewModels()

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        getResource()
        bindLoader()
        bindResource()
    }

    private fun getResource() {
        lifecycleScope.launch {
            viewModel.getTvShowsResource()
        }
    }

    private fun bindLoader() {
        viewModel.loader.observe(this) {
            parent?.showLoader(it)
        }
    }

    private fun bindResource() {
        viewModel.tvShowsDTOResource.observe(this) {
            when(it) {
                is Resource.Error -> {
                    Snackbar.make(binding.root,"Error: ${it.message}", Snackbar.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    val data: List<ItemModel> = it.data.orEmpty()
                    val items: List<ItemViewData> = data.map { item ->
                        ItemViewData(
                            item.title,
                            item.overview,
                            viewModel.getImageFullPath(item.posterPath),
                            viewModel.getImageFullPath(item.coverPath),
                            item.saved,
                            onDetail = {
                                detailItemViewModel.setItem(item)
                                navController?.navigate(R.id.action_tvShows_to_tvShowDetail)
                            },
                            onSave = {

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
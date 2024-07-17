package com.example.themoviedbclient.presentation.view.fragments.tvshow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.dto.tvshow.TvShowDTO
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.view.activity.MainActivity
import com.example.themoviedbclient.presentation.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewAdapter
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewData
import com.example.themoviedbclient.presentation.viewmodel.detail.item.DetailItemViewModel
import com.example.themoviedbclient.presentation.viewmodel.tvshow.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowsFragment: BaseFragmentList() {

    private val viewModel: TvShowsViewModel by activityViewModels()
    private val detailItemViewModel: DetailItemViewModel by activityViewModels()

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getTvShowsResource()
        }

        viewModel.tvShowsDTOResource.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    parent?.showLoader()
                }
                is Resource.Error -> {
                    parent?.hideLoader()
                    TODO("Handle Error UI")
                }
                is Resource.Success -> {
                    parent?.hideLoader()
                    val data: List<ItemModel> = it.data.orEmpty()
                    val items: List<ItemViewData> = data.map { item ->
                        ItemViewData(
                            item.title,
                            item.overview,
                            item.posterURL,
                            item.coverURL,
                            onDetail = {
                                detailItemViewModel.setItem(item)
                                navController?.navigate(R.id.action_tvShows_to_tvShowDetail)
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
package com.example.themoviedbclient.presentation.view.fragments.TrendingMovies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.data.model.Movie
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.view.MainActivity
import com.example.themoviedbclient.presentation.view.baseclass.ActivityBaseClass
import com.example.themoviedbclient.presentation.view.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolder
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolderItem
import com.example.themoviedbclient.presentation.view.fragments.TrendingMovies.TrendingMoviesAdapter.*
import com.example.themoviedbclient.presentation.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class TrendingMoviesFragment: BaseFragmentList() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private val parent: MainActivity? by lazy {
        activity as? MainActivity
    }

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.fetchTrendingMoviesResource()
        }

        viewModel.trendingMoviesResource.observe(this) {
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
                    val data: List<Movie> = it.data?.result.orEmpty()
                    val items: List<TrendingMoviesItem> = data.map { item ->
                        TrendingMoviesItem(viewModel.getPosterFullPathFrom(item))
                    }
                    val adapter = TrendingMoviesAdapter(items)
                    setAdapter(adapter)
                }
                else -> Unit
            }
        }
    }
}
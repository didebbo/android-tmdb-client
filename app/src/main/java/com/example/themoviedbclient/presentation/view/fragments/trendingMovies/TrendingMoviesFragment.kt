package com.example.themoviedbclient.presentation.view.fragments.trendingMovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.data.model.Movie
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.view.MainActivity
import com.example.themoviedbclient.presentation.view.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.fragments.trendingMovies.TrendingMoviesAdapter.*
import com.example.themoviedbclient.presentation.viewmodel.MainActivityViewModel
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
                        TrendingMoviesItem(
                            viewModel.getPosterFullPathFrom(item),
                            item.title,
                            item.overview
                        )
                    }
                    val adapter = TrendingMoviesAdapter(items)
                    setAdapter(adapter)
                }
                else -> Unit
            }
        }
    }
}
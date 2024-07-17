package com.example.themoviedbclient.presentation.view.fragments.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.data.model.movie.Movie
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.view.activity.MainActivity
import com.example.themoviedbclient.presentation.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.fragments.movie.adapter.MovieRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.fragments.movie.adapter.MovieViewHolderItem
import com.example.themoviedbclient.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment: BaseFragmentList() {

    private val viewModel: MoviesViewModel by viewModels()
    private val parent: MainActivity? by lazy {
        activity as? MainActivity
    }

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.fetchMoviesResource()
        }

        viewModel.moviesResource.observe(this) {
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
                    val items: List<MovieViewHolderItem> = data.map { item ->
                        MovieViewHolderItem(
                            viewModel.getPosterFullPathFrom(item),
                            item.title,
                            item.overview
                        )
                    }
                    val adapter = MovieRecyclerViewAdapter(items)
                    setAdapter(adapter)
                }
                else -> Unit
            }
        }
    }
}
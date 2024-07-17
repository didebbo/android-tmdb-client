package com.example.themoviedbclient.presentation.view.fragments.movie

import android.icu.text.IDNA
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.dto.movie.MovieDTO
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.presentation.view.activity.MainActivity
import com.example.themoviedbclient.presentation.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewAdapter
import com.example.themoviedbclient.presentation.view.adapter.item.ItemViewData
import com.example.themoviedbclient.presentation.viewmodel.detail.item.DetailItemViewModel
import com.example.themoviedbclient.presentation.viewmodel.movie.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment: BaseFragmentList() {

    private val viewModel: MoviesViewModel by activityViewModels()
    private val detailItemViewModel: DetailItemViewModel by activityViewModels()

    private val parent: MainActivity? by lazy {
        activity as? MainActivity
    }

    private val navController: NavController? by lazy {
        findNavController()
    }


    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getMoviesResource()
        }

        viewModel.moviesResource.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    parent?.showLoader()
                }
                is Resource.Error -> {
                    parent?.hideLoader()
                    Snackbar.make(binding.root,"Error: ${it.message}",Snackbar.LENGTH_SHORT).show()
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
                                navController?.navigate(R.id.action_movies_to_movieDetail)
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
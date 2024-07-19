package com.example.themoviedbclient.presentation.view.activity

import androidx.activity.viewModels
import com.example.themoviedbclient.R
import com.example.themoviedbclient.presentation.baseclass.activity.ActivityBaseClass
import com.example.themoviedbclient.presentation.viewmodel.item.MovieViewModel
import com.example.themoviedbclient.presentation.viewmodel.item.TvShowViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.MoviesViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.SavedItemsViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ActivityBaseClass() {

    override fun afterOnCreate() {
        super.afterOnCreate()

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.movies -> {
                    navController.popBackStack(R.id.itemDetail,false)
                    navController.navigate(R.id.movies)
                    true
                }
                R.id.tvShows -> {
                    navController.popBackStack(R.id.itemDetail,false)
                    navController.navigate(R.id.tvShows)
                    true
                }
                R.id.savedItemsTab -> {
                    navController.popBackStack(R.id.itemDetail,false)
                    navController.navigate(R.id.savedItemsTab)
                    true
                }
                else -> true
            }
        }
    }
}
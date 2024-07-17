package com.example.themoviedbclient.presentation.view.activity

import androidx.activity.viewModels
import com.example.themoviedbclient.presentation.baseclass.activity.ActivityBaseClass
import com.example.themoviedbclient.presentation.viewmodel.detail.item.DetailItemViewModel
import com.example.themoviedbclient.presentation.viewmodel.movie.MoviesViewModel
import com.example.themoviedbclient.presentation.viewmodel.tvshow.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ActivityBaseClass() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val tvShowsViewModel: TvShowsViewModel by viewModels()
    private val detailItemViewModel: DetailItemViewModel by viewModels()
}
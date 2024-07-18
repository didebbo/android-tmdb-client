package com.example.themoviedbclient.presentation.view.activity

import androidx.activity.viewModels
import com.example.themoviedbclient.presentation.baseclass.activity.ActivityBaseClass
import com.example.themoviedbclient.presentation.viewmodel.item.MovieViewModel
import com.example.themoviedbclient.presentation.viewmodel.item.TvShowViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.MoviesViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ActivityBaseClass() {

    val moviesViewModel: MoviesViewModel by viewModels()
    val tvShowsViewModel: TvShowsViewModel by viewModels()

    val movieViewModel: MovieViewModel by viewModels()
    val tvShowViewModel: TvShowViewModel by viewModels()
}
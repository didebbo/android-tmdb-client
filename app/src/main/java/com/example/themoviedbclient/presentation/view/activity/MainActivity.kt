package com.example.themoviedbclient.presentation.view.activity

import androidx.activity.viewModels
import com.example.themoviedbclient.presentation.baseclass.activity.ActivityBaseClass
import com.example.themoviedbclient.presentation.viewmodel.item.movie.MovieViewModel
import com.example.themoviedbclient.presentation.viewmodel.item.tvshow.TvShowViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.movie.MoviesViewModel
import com.example.themoviedbclient.presentation.viewmodel.items.tvshow.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ActivityBaseClass() {

    val moviesViewModel: MoviesViewModel by viewModels()
    val tvShowsViewModel: TvShowsViewModel by viewModels()

    val movieViewModel: MovieViewModel by viewModels()
    val tvShowViewModel: TvShowViewModel by viewModels()
}
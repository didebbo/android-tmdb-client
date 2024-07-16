package com.example.themoviedbclient.presentation.view

import androidx.activity.viewModels
import com.example.themoviedbclient.presentation.view.baseclass.ActivityBaseClass
import com.example.themoviedbclient.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: ActivityBaseClass() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
}
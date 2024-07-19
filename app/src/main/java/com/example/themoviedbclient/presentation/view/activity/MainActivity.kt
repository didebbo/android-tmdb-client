package com.example.themoviedbclient.presentation.view.activity

import com.example.themoviedbclient.R
import com.example.themoviedbclient.presentation.baseclass.activity.ActivityBaseClass
import dagger.hilt.android.AndroidEntryPoint

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
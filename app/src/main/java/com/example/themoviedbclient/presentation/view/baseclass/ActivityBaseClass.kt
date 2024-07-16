package com.example.themoviedbclient.presentation.view.baseclass

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.themoviedbclient.R
import com.example.themoviedbclient.databinding.BaseActivityLayoutBinding

abstract class ActivityBaseClass: AppCompatActivity() {

    private lateinit var baseActivityLayoutBinding: BaseActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivityLayoutBinding = BaseActivityLayoutBinding.inflate(layoutInflater)
        setContentView(baseActivityLayoutBinding.root)

        val navView = baseActivityLayoutBinding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navView.menu)


        setupActionBarWithNavController(navController, appBarConfiguration)
        baseActivityLayoutBinding.navView.setupWithNavController(navController)

        afterOnCreate()
    }

    open fun afterOnCreate() {}
}
package com.example.themoviedbclient.presentation.baseclass.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.themoviedbclient.R
import com.example.themoviedbclient.databinding.BaseActivityLayoutBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class ActivityBaseClass: AppCompatActivity() {

    private lateinit var binding: BaseActivityLayoutBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

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
                else -> true
            }
        }

        hideModalSystem()

        afterOnCreate()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun hideModalSystem() {
        binding.progressBar.visibility = View.GONE
        binding.modalSystem.visibility = View.GONE
    }
    private fun showLoader() {
        binding.modalSystem.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        hideModalSystem()
    }

    fun showLoader(state: Boolean) {
        when(state) {
            true -> {
                showLoader()
            }
            false -> {
                hideLoader()
            }
        }
    }

    open fun afterOnCreate() {}
}
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

abstract class ActivityBaseClass: AppCompatActivity() {

    private lateinit var binding: BaseActivityLayoutBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        val navView = binding.navView
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

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
    fun showLoader() {
        binding.modalSystem.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideLoader() {
        hideModalSystem()
    }

    open fun afterOnCreate() {}
}
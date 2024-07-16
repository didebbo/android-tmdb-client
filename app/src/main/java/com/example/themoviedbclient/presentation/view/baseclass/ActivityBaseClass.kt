package com.example.themoviedbclient.presentation.view.baseclass

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.themoviedbclient.R
import com.example.themoviedbclient.databinding.BaseActivityLayoutBinding

abstract class ActivityBaseClass: AppCompatActivity() {

    private lateinit var binding: BaseActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navView.menu)


        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        hideModalSystem()

        afterOnCreate()
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
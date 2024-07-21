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
    lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView

    private var alertOnAccept: (()->Unit)? = null
    private var alertOnDecline: (()->Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.alertAcceptButton.setOnClickListener {
            hideAlertView()
            alertOnAccept?.invoke()
        }

        binding.alertDeclineButton.setOnClickListener {
            hideAlertView()
            alertOnDecline?.invoke()
        }

        hideModalSystem()
        afterOnCreate()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun hideModalSystem() {
        hideLoader()
        hideAlertView()
    }
    private fun showLoader() {
        binding.systemLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.systemLoader.visibility = View.GONE
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

    fun showAlertView(message: String, onAccept: (()->Unit)? = null, onDecline: (()->Unit)? = null ) {
        binding.systemAlert.visibility = View.VISIBLE
        binding.alertDeclineButton.visibility = if(onDecline != null) View.VISIBLE else View.GONE
        binding.alertViewText.text = message
        alertOnAccept = onAccept
        alertOnDecline = onDecline
    }

    fun hideAlertView() {
        binding.systemAlert.visibility = View.GONE
    }

    open fun afterOnCreate() {}
}
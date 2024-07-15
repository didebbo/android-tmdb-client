package com.example.themoviedbclient.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.databinding.MainActivityLayoutBinding
import com.example.themoviedbclient.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private  lateinit var binding: MainActivityLayoutBinding

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        mainActivityViewModel.trendingMoviesResource.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    Log.i("[GN]", "Loading...")
                }
                is Resource.Success -> {
                    if(it.data != null) {
                        Log.i("[GN]", it.data.toString())
                    }
                }
                is Resource.Error -> {
                    if(it.message != null)
                        Log.i("[GN]", "Error: ${it.message}")
                }
                is Resource.Null -> {
                    Log.i("[GN]", "Null")
                }
            }
        }

        binding.buttonCall.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.fetchTrendingMoviesResource()
            }
        }
    }
}
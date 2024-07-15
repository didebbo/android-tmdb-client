package com.example.themoviedbclient.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbclient.data.api.MovieApiService
import com.example.themoviedbclient.data.api.Retrofit
import com.example.themoviedbclient.data.repository.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.repository.movie.MovieRemoteDataSourceImpl
import com.example.themoviedbclient.databinding.MainActivityLayoutBinding
import com.example.themoviedbclient.domain.repository.movie.MovieRepository
import com.example.themoviedbclient.domain.repository.movie.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.io.IOException

class MainActivity: AppCompatActivity() {
    private  lateinit var binding: MainActivityLayoutBinding

    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
    private lateinit var movieRepository: MovieRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieRemoteDataSource = MovieRemoteDataSourceImpl(Retrofit.movieApiService)
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource)
    }

    override fun onStart() {
        super.onStart()
        binding.buttonCall.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val data = movieRepository.getTrendingMovies("day", "en-US")
                    Log.i("[GN]", data.data.toString())
                } catch (e: IOException) {
                    Log.e("[GN]", "Network error: ${e.message}")
                } catch (e: Exception) {
                    Log.e("[GN]", "Unexpected error: ${e.message}")
                }
            }
        }
    }
}
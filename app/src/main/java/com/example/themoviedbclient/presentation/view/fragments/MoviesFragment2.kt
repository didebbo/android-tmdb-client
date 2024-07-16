package com.example.themoviedbclient.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.themoviedbclient.databinding.Movies2FragmentLayoutBinding
import com.example.themoviedbclient.databinding.MoviesFragmentLayoutBinding

class MoviesFragment2: Fragment() {
    private lateinit var binding: Movies2FragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Movies2FragmentLayoutBinding.inflate(inflater,container,false)
        return  binding.root
    }
}
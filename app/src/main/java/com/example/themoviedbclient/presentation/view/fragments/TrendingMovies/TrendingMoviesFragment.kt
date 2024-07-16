package com.example.themoviedbclient.presentation.view.fragments.TrendingMovies

import android.os.Bundle
import android.view.View
import com.example.themoviedbclient.presentation.view.baseclass.fragment.BaseFragmentList
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolder
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolderItem
import com.example.themoviedbclient.presentation.view.fragments.TrendingMovies.TrendingMoviesAdapter.*

class TrendingMoviesFragment: BaseFragmentList<TrendingMoviesAdapter.TrendingMoviesViewHolder,TrendingMoviesAdapter.TrendingMoviesItem>() {

    override fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.afterOnViewCreated(view, savedInstanceState)
        val adapter = TrendingMoviesAdapter(listOf(
            TrendingMoviesItem("Mario"),
            TrendingMoviesItem("Rossi"),
            TrendingMoviesItem("Verdi")
        ))
        setAdapter(adapter)
    }
}
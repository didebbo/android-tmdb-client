package com.example.themoviedbclient.presentation.view.fragments.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.themoviedbclient.presentation.view.fragments.ItemsFragment

class SavedItemsTabsAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        val fragment = ItemsFragment()
        val typeValue = if(position == 0) "savedMovies" else "savedTvShow"
        fragment.arguments = Bundle().apply {
            putString("type",typeValue)
        }
        return fragment
    }
}
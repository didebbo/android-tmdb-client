package com.example.themoviedbclient.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.themoviedbclient.databinding.SavedItemsTabsFragmentLayoutBinding
import com.example.themoviedbclient.presentation.view.activity.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SavedItemsTabsFragment: Fragment() {

    private lateinit var binding: SavedItemsTabsFragmentLayoutBinding

    private lateinit var savedItemsTabsAdapter: SavedItemsTabsAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SavedItemsTabsFragmentLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedItemsTabsAdapter = SavedItemsTabsAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = savedItemsTabsAdapter

        tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val title = if(position == 0) "Movies" else "TvShows"
            tab.text = title
        }.attach()
    }
}

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
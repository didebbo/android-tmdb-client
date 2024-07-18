package com.example.themoviedbclient.presentation.view.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.themoviedbclient.presentation.viewmodel.items.SavedItemsViewModel

class SavedItemsTabsFragment: Fragment() {

    private val savedItemsViewModel: SavedItemsViewModel by activityViewModels()

}
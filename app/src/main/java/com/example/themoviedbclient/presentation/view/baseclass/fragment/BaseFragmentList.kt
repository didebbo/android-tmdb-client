package com.example.themoviedbclient.presentation.view.baseclass.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.themoviedbclient.databinding.BaseFragmentListBinding
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolder
import com.example.themoviedbclient.presentation.view.baseclass.util.BaseViewHolderItem

abstract class BaseFragmentList<VH: BaseViewHolder<View,Item>,Item: BaseViewHolderItem>: Fragment() {

    private lateinit var binding: BaseFragmentListBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BaseFragmentListBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        afterOnViewCreated(view, savedInstanceState)
    }

    open fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {}

    fun setAdapter(adapter: BaseRecyclerViewAdapter<VH,Item>) {
        recyclerView.adapter = adapter
    }
}
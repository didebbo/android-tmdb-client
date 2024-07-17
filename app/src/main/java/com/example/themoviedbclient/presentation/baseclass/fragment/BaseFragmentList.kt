package com.example.themoviedbclient.presentation.baseclass.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedbclient.databinding.BaseFragmentListBinding
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseViewHolder
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseViewHolderItem

abstract class BaseFragmentList: Fragment() {

    lateinit var binding: BaseFragmentListBinding
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

    fun <VH: BaseViewHolder<View, I>, I: BaseViewHolderItem> setAdapter(adapter: BaseRecyclerViewAdapter<VH, I>) {
        recyclerView.adapter = adapter
    }
}
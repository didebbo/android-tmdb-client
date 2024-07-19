package com.example.themoviedbclient.presentation.baseclass.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedbclient.databinding.BaseFragmentListBinding
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseRecyclerViewAdapter
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseViewHolder
import com.example.themoviedbclient.presentation.baseclass.adapter.BaseViewHolderItem
import com.example.themoviedbclient.presentation.view.activity.MainActivity

abstract class BaseFragmentList: Fragment() {

    lateinit var binding: BaseFragmentListBinding
    private lateinit var recyclerView: RecyclerView

    val parent: MainActivity? by lazy {
        activity as? MainActivity
    }

    val navController: NavController? by lazy {
        findNavController()
    }

    private val actionBar: ActionBar? by lazy {
        parent?.supportActionBar
    }

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
        actionBar?.setDisplayHomeAsUpEnabled(false)
        afterOnViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        afterOnResume()
    }

    open fun afterOnViewCreated(view: View, savedInstanceState: Bundle?) {}

    open fun afterOnResume() {}

    fun <VH: BaseViewHolder<View, I>, I: BaseViewHolderItem> setAdapter(adapter: BaseRecyclerViewAdapter<VH, I>) {
        recyclerView.adapter = adapter
    }
}
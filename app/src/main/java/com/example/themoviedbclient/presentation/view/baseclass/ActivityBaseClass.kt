package com.example.themoviedbclient.presentation.view.baseclass

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.themoviedbclient.databinding.BaseActivityLayoutBinding

abstract class ActivityBaseClass: AppCompatActivity() {

    private lateinit var baseActivityLayoutBinding: BaseActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivityLayoutBinding = BaseActivityLayoutBinding.inflate(layoutInflater)
        setContentView(baseActivityLayoutBinding.root)
        afterOnCreate()
    }

    open fun afterOnCreate() {}


}
package com.firentistfw.kindlehighlights.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(layoutResId)
        initInteractions()
        initObservers()
    }

    protected open fun initInteractions() {}

    protected open fun initObservers() {}
}

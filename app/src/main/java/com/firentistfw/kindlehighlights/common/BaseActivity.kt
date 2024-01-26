package com.firentistfw.kindlehighlights.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    protected open fun initInteractions() {}
}

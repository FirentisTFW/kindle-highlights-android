package com.firentistfw.kindlehighlights.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.firentistfw.kindlehighlights.BuildConfig
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.databinding.ActivityMainBinding
import com.firentistfw.kindlehighlights.ui.addcategory.AddCategoryActivity
import com.firentistfw.kindlehighlights.ui.randomgenerator.RandomGeneratorActivity
import com.firentistfw.kindlehighlights.utils.ToastUtils

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, HighlightListFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (BuildConfig.DEBUG) {
            menuInflater.inflate(R.menu.home_menu_debug, menu)
        } else {
            menuInflater.inflate(R.menu.home_menu, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // TODO Implement navigation
            R.id.miCategories -> ToastUtils.showFeatureUnavailable(this)
            R.id.miAddCategory -> goToAddCategoryView()
            R.id.miAllHighlights -> ToastUtils.showFeatureUnavailable(this)
            R.id.miImportHighlights -> ToastUtils.showFeatureUnavailable(this)
            R.id.miRandomGenerator -> goToRandomGeneratorView()
        }

        return true
    }

    private fun goToAddCategoryView() {
        Intent(this, AddCategoryActivity::class.java).also {
            startActivity(it)
        }
    }
    private fun goToRandomGeneratorView() {
        Intent(this, RandomGeneratorActivity::class.java).also {
            startActivity(it)
        }
    }
}
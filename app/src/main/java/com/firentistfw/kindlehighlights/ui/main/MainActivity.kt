package com.firentistfw.kindlehighlights.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.databinding.ActivityMainBinding
import com.firentistfw.kindlehighlights.ui.addcategory.AddCategoryActivity
import com.firentistfw.kindlehighlights.utils.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModel()
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
        menuInflater.inflate(R.menu.home_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // TODO Implement navigation
            R.id.miCategories -> ToastUtils.showFeatureUnavailable(this)
            R.id.miAddCategory -> goToAddCategoryView()
            R.id.miAllHighlights -> ToastUtils.showFeatureUnavailable(this)
            R.id.miImportHighlights -> ToastUtils.showFeatureUnavailable(this)
        }

        return true
    }

    private fun goToAddCategoryView() {
        Intent(this, AddCategoryActivity::class.java).also {
            startActivity(it)
        }
    }
}
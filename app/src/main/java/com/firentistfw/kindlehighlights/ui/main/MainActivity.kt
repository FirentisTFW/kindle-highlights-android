package com.firentistfw.kindlehighlights.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import com.firentistfw.kindlehighlights.BuildConfig
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.databinding.ActivityMainBinding
import com.firentistfw.kindlehighlights.ui.addcategory.AddCategoryActivity
import com.firentistfw.kindlehighlights.ui.randomgenerator.RandomGeneratorActivity
import com.firentistfw.kindlehighlights.utils.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private val importHighlightsPermissionRequestCode = 0
    private val importHighlightsPermission = Manifest.permission.READ_EXTERNAL_STORAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment, HighlightListFragment())
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
            R.id.miImportHighlights -> importHighlights()
            R.id.miRandomGenerator -> goToRandomGeneratorView()
        }

        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != importHighlightsPermissionRequestCode) return
        if (permissions.isEmpty()) return

        for (i in permissions.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                when (permissions[i]) {
                    importHighlightsPermission -> viewModel.importHighlights()
                }
            }
        }
    }

    private fun importHighlights() {
        val storagePermissionStatus =
            ActivityCompat.checkSelfPermission(this, importHighlightsPermission)

        if (storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            viewModel.importHighlights()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(importHighlightsPermission), importHighlightsPermissionRequestCode
            )
        }
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
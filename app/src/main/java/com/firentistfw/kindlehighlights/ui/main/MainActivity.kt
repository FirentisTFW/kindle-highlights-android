package com.firentistfw.kindlehighlights.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.firentistfw.kindlehighlights.BuildConfig
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.common.RequestState
import com.firentistfw.kindlehighlights.databinding.ActivityMainBinding
import com.firentistfw.kindlehighlights.ui.addcategory.AddCategoryActivity
import com.firentistfw.kindlehighlights.ui.categorylist.CategoryListActivity
import com.firentistfw.kindlehighlights.ui.common.highlightlist.HighlightListFragment
import com.firentistfw.kindlehighlights.ui.common.highlightlist.HighlightListType
import com.firentistfw.kindlehighlights.ui.highlightlist.HighlightListActivity
import com.firentistfw.kindlehighlights.ui.randomgenerator.RandomGeneratorActivity
import com.firentistfw.kindlehighlights.ui.selections.SelectionsActivity
import com.firentistfw.kindlehighlights.utils.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private val importHighlightsPermissionRequestCode = 0

    private val pickTextFileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let(viewModel::importHighlightsFromFile)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO Remove this and add support for dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHighlightList, HighlightListFragment(HighlightListType.Daily))
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
            R.id.miCategories -> goToCategoryListView()
            R.id.miAddCategory -> goToAddCategoryView()
            R.id.miHighlights -> goToHighlightListView()
            R.id.miSelections -> goToSelectionsView()
            R.id.miImportHighlights -> ensureStoragePermissionAndImportHighlights()
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
                    getImportHighlightsPermission() -> importHighlights()
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.fileImportRequestState.observe(this) { state ->
            binding.pbImportHighlights.visibility = View.GONE
            binding.fragmentHighlightList.visibility = View.GONE

            when (state) {
                is RequestState.Error -> {
                    ToastUtils.showError(this, state.error)
                    binding.fragmentHighlightList.visibility = View.VISIBLE
                }

                is RequestState.Ongoing -> {
                    binding.pbImportHighlights.visibility = View.VISIBLE
                }

                is RequestState.Success -> {
                    binding.fragmentHighlightList.visibility = View.VISIBLE
                    ToastUtils.showSimpleToast(
                        this,
                        getString(R.string.main_highlightsImportedToast)
                    )
                }
            }
        }
    }

    private fun ensureStoragePermissionAndImportHighlights() {
        val permission = getImportHighlightsPermission()
        val storagePermissionStatus =
            ActivityCompat.checkSelfPermission(this, permission)

        if (storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            importHighlights()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(permission), importHighlightsPermissionRequestCode
            )
        }
    }

    private fun getImportHighlightsPermission(): String {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            return Manifest.permission.READ_MEDIA_IMAGES
        }

        return Manifest.permission.READ_EXTERNAL_STORAGE
    }

    private fun goToAddCategoryView() {
        Intent(this, AddCategoryActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun goToCategoryListView() {
        Intent(this, CategoryListActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun goToHighlightListView() {
        Intent(this, HighlightListActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun goToSelectionsView() {
        Intent(this, SelectionsActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun goToRandomGeneratorView() {
        Intent(this, RandomGeneratorActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun importHighlights() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
        }
        pickTextFileLauncher.launch(intent)
    }
}
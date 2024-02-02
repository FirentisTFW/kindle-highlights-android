package com.firentistfw.kindlehighlights.ui.addcategory

import android.os.Bundle
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.common.RequestState
import com.firentistfw.kindlehighlights.databinding.ActivityAddCategoryBinding
import com.firentistfw.kindlehighlights.utils.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCategoryActivity : BaseActivity() {
    private val viewModel: AddCategoryViewModel by viewModel()
    private lateinit var binding: ActivityAddCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initInteractions()
    }

    override fun initInteractions() {
        binding.btnAddCategory.setOnClickListener {
            onButtonTap()
        }

        viewModel.requestState.observe(this) { state ->
            binding.btnAddCategory.isEnabled = true
            when (state) {
                is RequestState.Error -> {
                    ToastUtils.showError(this, state.error)
                }

                is RequestState.Ongoing -> {
                    binding.btnAddCategory.isEnabled = false
                }

                is RequestState.Success -> {
                    ToastUtils.showSimpleToast(
                        this,
                        getString(R.string.addCategory_categoryAddedToast)
                    )
                }
            }
        }
    }

    private fun onButtonTap() {
        viewModel.addCategory(name = binding.etCategoryName.text.toString())
    }
}
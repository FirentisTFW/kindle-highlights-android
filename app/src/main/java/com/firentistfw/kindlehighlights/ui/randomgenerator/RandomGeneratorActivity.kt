package com.firentistfw.kindlehighlights.ui.randomgenerator

import android.os.Bundle
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.databinding.ActivityRandomGeneratorBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomGeneratorActivity : BaseActivity() {
    private val viewModel: RandomGeneratorViewModel by viewModel()
    private lateinit var binding: ActivityRandomGeneratorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomGeneratorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initInteractions() {
        super.initInteractions()

        binding.btnAddRandomBook.setOnClickListener {
            viewModel.addRandomBook()
        }

        binding.btnAddRandomHighlight.setOnClickListener {
            viewModel.addRandomHighlight()
        }
    }
}
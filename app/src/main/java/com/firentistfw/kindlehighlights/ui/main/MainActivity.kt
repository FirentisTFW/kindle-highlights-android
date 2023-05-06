package com.firentistfw.kindlehighlights.ui.main

import android.content.Intent
import android.os.Bundle
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.common.Constants
import com.firentistfw.kindlehighlights.common.mocks.Mocks
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsActivity
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsArguments
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    override val layoutResId = R.layout.activity_main

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInteractions()
    }

    override fun initInteractions() {
        btnHighlightDetails?.setOnClickListener {
            goToHighlightDetails()
        }
    }

    // FIXME Test only
    private fun goToHighlightDetails() {
        val intent = Intent(this, HighlightDetailsActivity::class.java)
        intent.putExtra(Constants.argumentsKey, HighlightDetailsArguments(Mocks.exampleHighlight))
        startActivity(intent)
    }
}
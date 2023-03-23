package com.firentistfw.kindlehighlights.ui.highlightdetails

import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HighlightDetailsActivity : BaseActivity() {
    override val layoutResId = R.layout.activity_highlight_details

    private val viewModel: HighlightDetailsViewModel by viewModel()
}
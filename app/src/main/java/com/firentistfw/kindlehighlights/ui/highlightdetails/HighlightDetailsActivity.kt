package com.firentistfw.kindlehighlights.ui.highlightdetails

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.common.Constants
import com.firentistfw.kindlehighlights.models.Highlight
import com.firentistfw.kindlehighlights.models.authorAndTitleDisplay
import kotlinx.android.synthetic.main.activity_highlight_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HighlightDetailsActivity : BaseActivity() {
    override val layoutResId = R.layout.activity_highlight_details

    private val viewModel: HighlightDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillInitialValues()
        divider.background = ContextCompat.getDrawable(this, R.drawable.divider)
    }

    private fun fillInitialValues() {
        val arguments = intent.getParcelableExtra<HighlightDetailsArguments>(Constants.argumentsKey)
        val highlight = arguments?.highlight

        quote.text = highlight?.content
        note.text = highlight?.note
        book.text = highlight?.book?.authorAndTitleDisplay
        date.text = highlight?.date
    }

}
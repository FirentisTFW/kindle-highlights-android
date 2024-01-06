package com.firentistfw.kindlehighlights.ui.highlightdetails

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.common.Constants
import com.firentistfw.kindlehighlights.databinding.ActivityHighlightDetailsBinding
import com.firentistfw.kindlehighlights.models.authorAndTitleDisplay
import org.koin.androidx.viewmodel.ext.android.viewModel

class HighlightDetailsActivity : BaseActivity() {
    private val viewModel: HighlightDetailsViewModel by viewModel()
    private lateinit var binding: ActivityHighlightDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighlightDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.divider.background = ContextCompat.getDrawable(this, R.drawable.divider)
        fillInitialValues()
    }

    private fun fillInitialValues() {
        val arguments = intent.getParcelableExtra<HighlightDetailsArguments>(Constants.argumentsKey)
        val highlight = arguments?.highlight

        binding.quote.text = highlight?.content
        binding.note.text = highlight?.note
        binding.book.text = highlight?.book?.authorAndTitleDisplay
        binding.date.text = highlight?.date
    }
}
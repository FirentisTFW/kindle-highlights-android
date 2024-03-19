package com.firentistfw.kindlehighlights.ui.highlightlist

import android.os.Bundle
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.databinding.ActivityHighlightListBinding
import com.firentistfw.kindlehighlights.ui.common.highlightlist.HighlightListFragment
import com.firentistfw.kindlehighlights.ui.common.highlightlist.HighlightListType

class HighlightListActivity : BaseActivity() {
    private lateinit var binding: ActivityHighlightListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHighlightListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_highlight_list)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHighlightList, HighlightListFragment(HighlightListType.All))
            .commit()
    }
}
package com.firentistfw.kindlehighlights.ui.highlightdetails

import android.os.Parcelable
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import kotlinx.parcelize.Parcelize

@Parcelize
data class HighlightDetailsArguments(val highlight: CompleteHighlight): Parcelable
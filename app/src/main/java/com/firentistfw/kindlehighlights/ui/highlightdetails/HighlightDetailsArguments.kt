package com.firentistfw.kindlehighlights.ui.highlightdetails

import android.os.Parcelable
import com.firentistfw.kindlehighlights.models.Highlight
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HighlightDetailsArguments(val highlight: Highlight): Parcelable
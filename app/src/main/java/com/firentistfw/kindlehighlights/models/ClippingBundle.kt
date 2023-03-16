package com.firentistfw.kindlehighlights.models

data class ClippingBundle(
    val highlightRaw: String,
    val noteRaw: String? = null,
)

fun MutableList<ClippingBundle>.updateLastElementNote(note: String): MutableList<ClippingBundle> =
    apply {
        this[this.lastIndex] = this[this.lastIndex].copy(noteRaw = note)
    }

fun MutableList<ClippingBundle>.addClippingBundleWithHighlight(highlight: String): MutableList<ClippingBundle> =
    apply {
        add(
            ClippingBundle(
                highlightRaw = highlight,
            ),
        )
    }
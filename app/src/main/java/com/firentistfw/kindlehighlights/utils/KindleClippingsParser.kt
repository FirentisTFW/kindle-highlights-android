package com.firentistfw.kindlehighlights.utils

import com.firentistfw.kindlehighlights.extensions.filterNotEmpty
import com.firentistfw.kindlehighlights.models.ClippingBundle
import com.firentistfw.kindlehighlights.models.addClippingBundleWithHighlight
import com.firentistfw.kindlehighlights.models.updateLastElementNote

class KindleClippingsParser() {
    fun separateClippings(clippingsText: String, separator: String = "=========="): List<String> =
        clippingsText.split(separator).map(String::trim).filterNotEmpty()

    fun filterOutBookmarks(clippings: List<String>): List<String> {
        val bookmarkIndication = "Your Bookmark on"
        return clippings.filter { !it.contains(bookmarkIndication) }
    }

    fun combineClippingsIntoBundles(clippings: List<String>): List<ClippingBundle> {
        return clippings.fold(mutableListOf<ClippingBundle>()) { list, item ->
            if (item.isNote)
                list.updateLastElementNote(item)
            else
                list.addClippingBundleWithHighlight(item)
        }
    }

    /*
        Parsing process

        My Clippings.txt -> List<Highlight>

        DONE 1. Input file - My Clippings.txt
        DONE 2. Separate segments by ====== signs and return list of strings (separate parts)
        DONE 3. Filter out bookmarks
        DONE 4. Combine Highlights and Notes into Bundles -> {highlight: String, note: String}
        5. For each bundle: Transform string into Highlights (separate Highlight and Note parsers)

     */
}

private val String.isNote: Boolean
    get() = contains("Your Note on")
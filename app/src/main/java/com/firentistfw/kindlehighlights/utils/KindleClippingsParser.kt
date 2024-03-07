package com.firentistfw.kindlehighlights.utils

import com.firentistfw.kindlehighlights.extensions.filterNotEmpty
import com.firentistfw.kindlehighlights.extensions.removeParenthesis
import com.firentistfw.kindlehighlights.extensions.substringFrom
import com.firentistfw.kindlehighlights.extensions.substringUntil
import com.firentistfw.kindlehighlights.models.*

class KindleClippingsParser() {
    fun createHighlightsFromRawClippingsInput(input: String): List<ImportedHighlight> {
        val rawClippings = separateClippings(input)
        val rawClippingsWithoutBookmarks = filterOutBookmarks(rawClippings)
        val clippingBundles = combineClippingsIntoBundles(rawClippingsWithoutBookmarks)

        return transformRawClippingsToModels(clippingBundles)
    }

    fun separateClippings(clippingsText: String, separator: String = "=========="): List<String> =
        clippingsText.split(separator).map(String::trim).filterNotEmpty()

    fun filterOutBookmarks(clippings: List<String>): List<String> =
        clippings.filter { !it.isBookmark }

    fun combineClippingsIntoBundles(clippings: List<String>): List<ClippingBundle> {
        return clippings.fold(mutableListOf()) { list, clipping ->
            if (clipping.isNote)
                list.updateLastElementNote(clipping)
            else
                list.addClippingBundleWithHighlight(clipping)
        }
    }

    fun transformRawClippingsToModels(clippingBundles: List<ClippingBundle>): List<ImportedHighlight> {
        return clippingBundles.map {
            val clippingParts = it.highlightRaw.split("\n").filterNotEmpty()
            val note = it.noteRaw?.split("\n")?.filterNotEmpty()?.last()
            val bookTitleAndAuthor = clippingParts.first().trim()
            val title = bookTitleAndAuthor.substringUntil("(").trim()
            val author = bookTitleAndAuthor.substringFrom("(").removeParenthesis()
            val content = clippingParts.last()
            val date = clippingParts[1].substringFrom(",", shouldIncludeStart = false).trim()
            ImportedHighlight(
                book = ImportedBook(
                    author = author,
                    title = title,
                ),
                categories = emptyList(),
                content = content,
                date = date,
                note = note,
            )
        }
    }

}

private val String.isBookmark: Boolean
    get() = contains("Your Bookmark on")

private val String.isNote: Boolean
    get() = contains("Your Note on")
package com.firentistfw.kindlehighlights.utils

import com.firentistfw.kindlehighlights.models.ImportedBook
import com.firentistfw.kindlehighlights.models.ClippingBundle
import com.firentistfw.kindlehighlights.models.ImportedHighlight
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class KindleClippingsParserTest {
    private val exampleBookmarkRaw = "Book Title (Author Name)\n" +
            "- Your Bookmark on Location 817 | Added on Friday, June 19, 2020 5:32:31 PM\n"

    private val exampleHighlightRaw = "Book Title (Author Name)\n" +
            "- Your Highlight on Location 991-994 | Added on Sunday, June 21, 2020 1:53:07 PM\n" +
            "\n" +
            "Text in the book - oooo.\n"

    private val exampleNoteRaw = "Book Title (Author Name)\n" +
            "- Your Note on Location 994 | Added on Sunday, June 21, 2020 1:54:16 PM\n" +
            "\n" +
            "My note on the text in the book\n"

    private val exampleHighlight2Raw = "Book Title (Author Name)\n" +
            "- Your Highlight on Location 998 | Added on Sunday, June 21, 2020 1:58:43 PM\n" +
            "\n" +
            "Another text in the book - oooo.\n"

    private val input = exampleBookmarkRaw +
            "==========\n" +
            exampleHighlightRaw +
            "==========\n" +
            exampleNoteRaw +
            "==========\n" +
            exampleHighlight2Raw +
            "=========="

    private val separatedClippings = listOf<String>(
        exampleBookmarkRaw.trim(),
        exampleHighlightRaw.trim(),
        exampleNoteRaw.trim(),
        exampleHighlight2Raw.trim(),
    )

    private val clippingsWithoutBookmarks = listOf<String>(
        exampleHighlightRaw.trim(),
        exampleNoteRaw.trim(),
        exampleHighlight2Raw.trim(),
    )

    private val exampleClippingBundleWithNote = ClippingBundle(
        highlightRaw = exampleHighlightRaw.trim(),
        noteRaw = exampleNoteRaw.trim(),
    )

    private val exampleClippingBundleWithoutNote = ClippingBundle(
        highlightRaw = exampleHighlight2Raw.trim(),
    )

    private val exampleClippingBundles = listOf(
        exampleClippingBundleWithNote,
        exampleClippingBundleWithoutNote,
    )

    private val exampleBook = ImportedBook(
        author = "Author Name",
        title = "Book Title",
    )

    private val exampleHighlight = ImportedHighlight(
        book = exampleBook,
        categories = emptyList(),
        content = "Text in the book - oooo.",
        date = "June 21, 2020 1:53:07 PM",
        note = "My note on the text in the book",
    )

    private val exampleHighlight2 = ImportedHighlight(
        book = exampleBook,
        categories = emptyList(),
        content = "Another text in the book - oooo.",
        date = "June 21, 2020 1:58:43 PM",
    )

    private val exampleHighlights = listOf(
        exampleHighlight,
        exampleHighlight2,
    )

    @Test
    fun separateClippings() {
        val sut = KindleClippingsParser()
        val result = sut.separateClippings(clippingsText = input, separator = "==========")

        assertThat(result).isEqualTo(separatedClippings)
    }

    @Test
    fun filterOutBookmarks() {
        val sut = KindleClippingsParser()
        val result = sut.filterOutBookmarks(separatedClippings)

        assertThat(result).isEqualTo(clippingsWithoutBookmarks)
    }

    @Test
    fun combineClippingsIntoBundles() {
        val sut = KindleClippingsParser()
        val result = sut.combineClippingsIntoBundles(clippingsWithoutBookmarks)

        assertThat(result).isEqualTo(exampleClippingBundles)
    }

    @Test
    fun transformRawClippingsToModels() {
        val sut = KindleClippingsParser()
        val result = sut.transformRawClippingsToModels(exampleClippingBundles)

        assertThat(result).isEqualTo(exampleHighlights)
    }
}

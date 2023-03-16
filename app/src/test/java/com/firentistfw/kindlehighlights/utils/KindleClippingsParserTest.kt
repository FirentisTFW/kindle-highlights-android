package com.firentistfw.kindlehighlights.utils


import com.firentistfw.kindlehighlights.models.ClippingBundle
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class KindleClippingsParserTest {
    private val exampleBookmark = "Book Name (Author Name)\n" +
            "- Your Bookmark on Location 817 | Added on Friday, June 19, 2020 5:32:31 PM\n"

    private val exampleHighlight = "Book Name (Author Name)\n" +
            "- Your Highlight on Location 991-994 | Added on Sunday, June 21, 2020 1:53:07 PM\n" +
            "\n" +
            "Text in the book - oooo.\n"

    private val exampleNote = "Book Name (Author Name)\n" +
            "- Your Note on Location 994 | Added on Sunday, June 21, 2020 1:54:16 PM\n" +
            "\n" +
            "My note on the text in the book\n"

    private val exampleHighlight2 = "Book Name (Author Name)\n" +
            "- Your Highlight on Location 998 | Added on Sunday, June 21, 2020 1:58:43 PM\n" +
            "\n" +
            "Another text in the book - oooo.\n"

    private val input = exampleBookmark +
            "==========\n" +
            exampleHighlight +
            "==========\n" +
            exampleNote +
            "==========\n" +
            exampleHighlight2 +
            "=========="

    private val separatedClippings = listOf<String>(
        exampleBookmark.trim(),
        exampleHighlight.trim(),
        exampleNote.trim(),
        exampleHighlight2.trim(),
    )

    private val clippingsWithoutBookmarks = listOf<String>(
        exampleHighlight.trim(),
        exampleNote.trim(),
        exampleHighlight2.trim(),
    )

    private val exampleClippingBundleWithNote = ClippingBundle(
        highlightRaw = exampleHighlight.trim(),
        noteRaw = exampleNote.trim(),
    )

    private val exampleClippingBundleWithoutNote = ClippingBundle(
        highlightRaw = exampleHighlight2.trim(),
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
        val result = sut.filterOutBookmarks(clippings = separatedClippings)

        assertThat(result).isEqualTo(clippingsWithoutBookmarks)
    }

    @Test
    fun combineClippingsIntoBundles() {
        val sut = KindleClippingsParser()
        val result = sut.combineClippingsIntoBundles(clippings = clippingsWithoutBookmarks)

        assertThat(result).isEqualTo(
            listOf(
                exampleClippingBundleWithNote,
                exampleClippingBundleWithoutNote,
            ),
        )
    }
}
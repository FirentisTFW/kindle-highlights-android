package com.firentistfw.kindlehighlights.ui.highlightdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.ui.styles.Colors
import com.firentistfw.kindlehighlights.ui.styles.Spacing
import com.firentistfw.kindlehighlights.ui.styles.TextStyles

// FIXME Finish this screen

@Composable
fun HighlightDetailsScreen(
    state: DataState<CompleteHighlight>,
) {
    when (state) {
        is DataState.Error -> Text(text = "An error occurred", modifier = Modifier.fillMaxSize())
        is DataState.Loading -> CircularProgressIndicator()
        is DataState.Success -> LoadedBody()
//        is DataState.Success -> LoadedBody(state.data)
    }
}

@Composable
@Preview
private fun LoadedBody(
//    highlight: CompleteHighlight,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.white)
            .padding(Spacing.xl),
        verticalArrangement = Arrangement.spacedBy(Spacing.l)
    ) {
        QuoteSection(quote = "80% of success is showing up every day")
        Divider()
        BookSection("Jeff Olson", "Slight Edge")
        DateSection("May 28, 2023")
        SectionHeader("Categories", R.drawable.ic_label)
    }
}

@Composable
private fun QuoteSection(quote: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.m)
    ) {
        SectionHeader("Quote", R.drawable.ic_open_book)
        Text(
            text = quote,
            style = TextStyles.quote,
        )
    }
}

@Composable
private fun BookSection(authorName: String, bookTitle: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.m)
    ) {
        SectionHeader("Book", R.drawable.ic_open_book)
        Text(
            text = authorName,
            style = TextStyles.content,
        )
        Text(
            text = bookTitle,
            style = TextStyles.content,
        )
    }
}

@Composable
private fun DateSection(date: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.m)
    ) {
        SectionHeader("Date", R.drawable.ic_calendar)
        Text(
            text = date,
            style = TextStyles.content,
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    drawableRes: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = title,
            modifier = Modifier
                .size(24.dp)
        )
        Box(modifier = Modifier.width(Spacing.m))
        Text(
            text = title.uppercase(),
            style = TextStyles.headlineMedium,
        )
    }
}

@Composable
private fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Colors.lightGrey)
    )
}

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import com.firentistfw.kindlehighlights.ui.common.KHButton
import com.firentistfw.kindlehighlights.ui.common.KHButtonType
import com.firentistfw.kindlehighlights.ui.styles.KHColors
import com.firentistfw.kindlehighlights.ui.styles.KHSpacings
import com.firentistfw.kindlehighlights.ui.styles.KHTextStyles

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
            .background(KHColors.white)
            .padding(KHSpacings.xl),
        verticalArrangement = Arrangement.spacedBy(KHSpacings.l)
    ) {
        QuoteSection(quote = "80% of success is showing up every day")
        Divider()
        BookSection("Jeff Olson", "Slight Edge")
        DateSection("May 28, 2023")
        CategoriesSection(listOf())
        KHButton(
            text = "Manage categories",
            onClick = {
                // FIXME Implement
            }
        )
        KHButton(
            text = "Remove highlight",
            type = KHButtonType.Danger,
            onClick = {
                // FIXME Implement
            }
        )
    }
}

@Composable
private fun QuoteSection(quote: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(KHSpacings.m)
    ) {
        SectionHeader("Quote", R.drawable.ic_open_book)
        Text(
            text = quote,
            style = KHTextStyles.quote,
        )
    }
}

@Composable
private fun BookSection(authorName: String, bookTitle: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(KHSpacings.m)
    ) {
        SectionHeader("Book", R.drawable.ic_open_book)
        Text(
            text = authorName,
            style = KHTextStyles.content,
        )
        Text(
            text = bookTitle,
            style = KHTextStyles.content,
        )
    }
}

@Composable
private fun DateSection(date: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(KHSpacings.m)
    ) {
        SectionHeader("Date", R.drawable.ic_calendar)
        Text(
            text = date,
            style = KHTextStyles.content,
        )
    }
}

@Composable
private fun CategoriesSection(categories: List<DBCategory>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(KHSpacings.m)
    ) {
        SectionHeader("Categories", R.drawable.ic_label)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(KHSpacings.m)
        ) {
            itemsIndexed(categories) { index, item ->
                Text(
                    text = "- ${item.name}",
                    style = KHTextStyles.content,
                )
            }
        }
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
        Box(modifier = Modifier.width(KHSpacings.m))
        Text(
            text = title.uppercase(),
            style = KHTextStyles.headlineMedium,
        )
    }
}

@Composable
private fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(KHColors.lightGrey)
    )
}

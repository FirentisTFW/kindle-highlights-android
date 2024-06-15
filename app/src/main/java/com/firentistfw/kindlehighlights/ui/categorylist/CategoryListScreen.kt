package com.firentistfw.kindlehighlights.ui.categorylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import com.firentistfw.kindlehighlights.ui.styles.KHColors
import com.firentistfw.kindlehighlights.ui.styles.KHTextStyles
import java.util.UUID

@Composable
fun CategoryListScreen(
    dataState: DataState<List<DBCategory>>?,
    onCategoryClick: (categoryId: UUID) -> Unit,
) {
    when (dataState) {
        is DataState.Error -> Text(text = "An error occurred", modifier = Modifier.fillMaxSize())
        is DataState.Loading, null -> CircularProgressIndicator()
        is DataState.Success -> LazyColumn {
            itemsIndexed(dataState.data) { index, item ->
                CategoryListCell(
                    name = item.name,
                    onCategoryClick = { onCategoryClick(item.categoryId) },
                )
                if (index < dataState.data.size - 1) {
                    Divider()
                }
            }
        }

    }
}

@Composable
private fun CategoryListCell(
    name: String,
    onCategoryClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCategoryClick)
            .padding(12.dp)
    ) {
        Text(
            text = name,
            style = KHTextStyles.subtitle,
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

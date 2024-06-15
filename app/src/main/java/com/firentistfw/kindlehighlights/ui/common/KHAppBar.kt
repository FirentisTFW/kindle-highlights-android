package com.firentistfw.kindlehighlights.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import com.firentistfw.kindlehighlights.ui.styles.KHColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KHAppBar(
    title: String,
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarColors(
            containerColor = KHColors.teal,
            scrolledContainerColor = KHColors.teal,
            navigationIconContentColor = KHColors.white,
            titleContentColor = KHColors.white,
            actionIconContentColor = KHColors.white,
        ),
    )
}
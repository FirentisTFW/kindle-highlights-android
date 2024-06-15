package com.firentistfw.kindlehighlights.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.firentistfw.kindlehighlights.ui.styles.KHColors
import com.firentistfw.kindlehighlights.ui.styles.KHTextStyles

@Composable
fun KHButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium.copy(
            topStart = ZeroCornerSize,
            topEnd = ZeroCornerSize,
            bottomStart = ZeroCornerSize,
            bottomEnd = ZeroCornerSize
        ),
        enabled = enabled,
        colors = ButtonColors(
            containerColor = KHColors.teal,
            contentColor = KHColors.white,
            disabledContainerColor = KHColors.lightGrey,
            disabledContentColor = KHColors.white,
        ),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
        )
    ) {
        Text(text.uppercase(), style = KHTextStyles.primaryButton)
    }
}
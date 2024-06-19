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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.firentistfw.kindlehighlights.ui.styles.KHColors
import com.firentistfw.kindlehighlights.ui.styles.KHTextStyles

enum class KHButtonType {
    Primary,
    Danger;

    val textColor: Color
        get() = when (this) {
            Primary -> KHColors.white
            Danger -> KHColors.red
        }

    val colors: ButtonColors
        get() = when (this) {
            Primary -> ButtonColors(
                containerColor = KHColors.teal,
                contentColor = KHColors.white,
                disabledContainerColor = KHColors.lightGrey,
                disabledContentColor = KHColors.white,
            )
            Danger -> ButtonColors(
                    containerColor = KHColors.white,
                    contentColor = KHColors.red,
                    disabledContainerColor = KHColors.lightGrey,
                    disabledContentColor = KHColors.white,
                )
        }
}

@Composable
fun KHButton(
    text: String,
    type: KHButtonType = KHButtonType.Primary,
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
        colors = type.colors,
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
        )
    ) {
        Text(text.uppercase(), style = KHTextStyles.primaryButton, color = type.textColor)
    }
}
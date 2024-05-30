package com.firentistfw.kindlehighlights.ui.styles

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.firentistfw.kindlehighlights.R

object TextStyles {
    private val robotoFontFamily = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_medium_italic, FontWeight.Medium, FontStyle.Italic),
    )

    val subtitle = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        color = Colors.black,
    )
}
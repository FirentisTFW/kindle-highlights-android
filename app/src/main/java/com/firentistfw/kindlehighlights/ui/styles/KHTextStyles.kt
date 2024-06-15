package com.firentistfw.kindlehighlights.ui.styles

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.firentistfw.kindlehighlights.R

object KHTextStyles {
    private val robotoFontFamily = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_medium_italic, FontWeight.Medium, FontStyle.Italic),
    )

    val content = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.05.sp,
        color = KHColors.black,
        lineHeight = 20.sp,
    )

    val headlineMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.05.sp,
        color = KHColors.black,
    )

    val subtitle = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        color = KHColors.black,
    )

    val primaryButton = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = KHColors.white,
        letterSpacing = 1.3.sp,
    )

    val quote = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        fontStyle = FontStyle.Italic,
        color = KHColors.black,
        lineHeight = 22.sp,
    )
}
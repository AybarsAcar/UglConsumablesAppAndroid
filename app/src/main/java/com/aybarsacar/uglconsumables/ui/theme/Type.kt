package com.aybarsacar.uglconsumables.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aybarsacar.uglconsumables.R


private val Montserrat = FontFamily(
  Font(R.font.mont_regular),
  Font(R.font.mont_italic, FontWeight.Normal, FontStyle.Italic),

  Font(R.font.mont_bold, FontWeight.Bold),
  Font(R.font.mont_bold_italic, FontWeight.Bold, FontStyle.Italic),

  Font(R.font.mont_light, FontWeight.Light),
  Font(R.font.mont_extra_light_italic, FontWeight.Light, FontStyle.Italic),

  Font(R.font.mont_thing, FontWeight.Thin),
  Font(R.font.mont_thin_italic, FontWeight.Thin, FontStyle.Italic),
)


// Set of Material typography styles to start with
val Typography = Typography(
  body1 = TextStyle(
    fontFamily = Montserrat,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  )
  /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
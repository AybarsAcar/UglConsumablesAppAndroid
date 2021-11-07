package com.aybarsacar.uglconsumables.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
  primary = DefaultBlue,
  primaryVariant = DarkBlue,
  secondary = BlueGrey
)

private val LightColorPalette = lightColors(
  primary = DefaultBlue,
  primaryVariant = BlueGrey,
  secondary = LightBlue

  /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun UglConsumablesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}
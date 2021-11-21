package com.aybarsacar.uglconsumables.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val DefaultBlue = Color(0xFF4267B2)
val DarkBlue = Color(0xff3d5a80)
val LightBlue = Color(0xff98c1d9)
val DaintreeBlue = Color(0xff01202d)

val DefaultComplementary = Color(0xFFCf751f)

val BlueGrey = Color(0xFF898F9C)

val VeryLightGray = Color(0xFFFAFAFA)
val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)
val SurfaceGray = Color(0xFF212121)


// Error colors
val ErrorLight = Color(0xFFB00020)
val ErrorDark = Color(0xFFCF6679)


val Colors.loginRegisterScreenBackgroundColor: Brush
  @Composable
  get() = if (isLight) {
    Brush.verticalGradient(listOf(DarkBlue, LightBlue))
  } else {
    Brush.verticalGradient(listOf(Color.Black, DaintreeBlue))
  }

val Colors.uglLogoColor: Color
  @Composable
  get() = if (isLight) Color.White else DefaultBlue


val Colors.topAppBarContentColor: Color
  @Composable
  get() = if (isLight) Color.White else LightGray


val Colors.topAppBarBackGroundColor: Color
  @Composable
  get() = if (isLight) DefaultBlue else Color.Black


val Colors.fabBackgroundColor: Color
  @Composable
  get() = if (isLight) DefaultComplementary else DefaultBlue

val Colors.fabIconColor: Color
  @Composable
  get() = if (isLight) Color.White else Color.Black

val Colors.taskItemBackgroundColor: Color
  @Composable
  get() = if (isLight) Color.White else SurfaceGray

val Colors.taskItemTextColor: Color
  @Composable
  get() = if (isLight) DarkGray else LightGray

val Colors.errorAndDeleteBackgroundColor: Color
  @Composable
  get() = if (isLight) ErrorLight else ErrorDark

val Colors.splashScreenBackground: Color
  @Composable
  get() = if (isLight) DarkBlue else Color.Black

val Colors.itemBorderColor: Color
  @Composable
  get() = if (isLight) LightGray else Color.Black
package com.aybarsacar.uglconsumables.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val DefaultBlue = Color(0xFF4267B2)
val DarkBlue = Color(0xff3d5a80)
val LightBlue = Color(0xff98c1d9)

val BlueGrey = Color(0xFF898F9C)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)


// Error colors
val ErrorLight = Color(0xFFB00020)
val ErrorDark = Color(0xFFCF6679)


val Colors.loginRegisterScreenBackgroundColor: Color
  @Composable
  get() = if (isLight) DarkBlue else Color.Black


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
  get() = if (isLight) DarkBlue else LightBlue

val Colors.fabIconColor: Color
  @Composable
  get() = if (isLight) Color.White else Color.Black

val Colors.taskItemBackgroundColor: Color
  @Composable
  get() = if (isLight) Color.White else DarkGray

val Colors.taskItemTextColor: Color
  @Composable
  get() = if (isLight) DarkGray else LightGray

val Colors.errorAndDeleteBackgroundColor: Color
  @Composable
  get() = if (isLight) ErrorLight else ErrorDark

val Colors.splashScreenBackground: Color
  @Composable
  get() = if (isLight) DarkBlue else Color.Black
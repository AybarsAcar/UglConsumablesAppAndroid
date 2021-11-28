package com.aybarsacar.uglconsumables.navigation

import android.annotation.SuppressLint


/**
 * List of screens for UglConsumables App
 */
sealed class Screen(val route: String) {

  @SuppressLint("CustomSplashScreen")
  object UglSplashScreen : Screen("ugl_splash_screen")

  object LoginRegister : Screen("login_register")

  object Home : Screen(route = "home")

  object CreateEdit : Screen(route = "create_edit") {
    fun createRoute(id: Int) = "create_edit/$id"
  }
}
package com.aybarsacar.uglconsumables.navigation


/**
 * List of screens for UglConsumables App
 */
sealed class Screen(val route: String) {

  object LoginRegister : Screen("login_register")

  object Home : Screen(route = "home")

  object CreateEdit : Screen(route = "create_edit") {
    fun createRoute(id: Int) = "create_edit/$id"
  }
}
package com.aybarsacar.uglconsumables.view


sealed class Screen(val route: String) {

  object LoginRegisterScreen : Screen("login_register_screen")

  object HomeScreen : Screen("home_screen")
}
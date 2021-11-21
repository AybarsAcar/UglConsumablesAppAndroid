package com.aybarsacar.uglconsumables.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavigationItem(
  val route: String,
  val title: String,
  val icon: ImageVector
) {

  object HomeScreen : NavigationItem(route = "home", title = "Home", icon = Icons.Default.Home)

  object ProfileScreen : NavigationItem(route = "profile/{username}", title = "Profile", icon = Icons.Default.Person) {
    fun createRoute(username: String) = "profile/$username"
  }

  object OrdersScreen : NavigationItem("orders", title = "Orders", icon = Icons.Default.Mail)

  object SettingsScreen : NavigationItem(route = "settings", title = "Settings", icon = Icons.Default.Settings)
}
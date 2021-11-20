package com.aybarsacar.uglconsumables.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * List of screens for UglConsumables App
 */
sealed class Screen(
  val route: String,
  val title: String? = null,
  val icon: ImageVector? = null
) {

  object LoginRegisterScreen : Screen("login_register")

  object HomeScreen : Screen(route = "home", title = "Home", icon = Icons.Default.Home)

  object ProfileScreen : Screen(route = "profile/{username}", title = "Profile", icon = Icons.Default.Person) {
    fun createRoute(username: String) = "profile/$username"
  }

  object OrdersScreen : Screen("orders")

  object OrderDetailsScreen : Screen("orders/{orderId}") {
    fun createRoute(orderId: Int) = "profile/$orderId"
  }

  object CreateEditConsumableScreen : Screen("create_edit_consumable/{consumableId}") {
    fun createRoute(consumableId: Int? = null) = "create_edit_consumable/$consumableId"
  }

  object CreateEditAreaOfWorkScreen : Screen("create_edit_area_of_work/{areaOfWorkId}") {
    fun createRoute(areaOfWorkId: Int? = null) = "create_edit_area_of_work/$areaOfWorkId"
  }

  object SettingsScreen : Screen(route = "settings", title = "Settings", icon = Icons.Default.Settings)
}
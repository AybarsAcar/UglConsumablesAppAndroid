package com.aybarsacar.uglconsumables.view


/**
 * List of screens for UglConsumables App
 */
sealed class Screen(val route: String) {

  object LoginRegisterScreen : Screen("login_register")

  object HomeScreen : Screen("home")

  object ProfileScreen : Screen("profile/{username}") {
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
}
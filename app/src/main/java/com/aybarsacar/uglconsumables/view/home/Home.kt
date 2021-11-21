package com.aybarsacar.uglconsumables.view.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.navigation.NavigationItem
import com.aybarsacar.uglconsumables.navigation.Router
import com.aybarsacar.uglconsumables.ui.theme.fabBackgroundColor
import com.aybarsacar.uglconsumables.ui.theme.fabIconColor
import com.aybarsacar.uglconsumables.view.home.components.BottomHomeBar
import com.aybarsacar.uglconsumables.view.home.components.HomeAppBar
import com.aybarsacar.uglconsumables.view.home.home_screen.HomeScreen


@ExperimentalMaterialApi
@Composable
fun Home(
  externalRouter: Router,
) {

  val navController = rememberNavController()

  val tabs = listOf(
    NavigationItem.HomeScreen,
    NavigationItem.ProfileScreen,
    NavigationItem.OrdersScreen,
    NavigationItem.SettingsScreen
  )


  Scaffold(
    topBar = {
      HomeAppBar()
    },
    floatingActionButton = {
      CreateNewOrderFab()
    },
    isFloatingActionButtonDocked = true,
    floatingActionButtonPosition = FabPosition.Center,
    bottomBar = {
      BottomHomeBar(navHostController = navController, tabs = tabs)
    },
    backgroundColor = MaterialTheme.colors.surface
  ) {
    // page content here

    Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {

      NavHost(navController = navController, startDestination = NavigationItem.HomeScreen.route) {

        composable(route = NavigationItem.HomeScreen.route) {
          HomeScreen()
        }

        composable(route = NavigationItem.ProfileScreen.route) {
          Text(text = "Profile Screen")
        }

        composable(route = NavigationItem.OrdersScreen.route) {
          Text(text = "Orders Screen")
        }

        composable(route = NavigationItem.SettingsScreen.route) {
          Text(text = "Settings Screen")
        }
      }
    }
  }
}


@Composable
fun CreateNewOrderFab() {
  FloatingActionButton(
    onClick = {

    },
    shape = RoundedCornerShape(50),
    backgroundColor = MaterialTheme.colors.fabBackgroundColor

  ) {
    Icon(
      imageVector = Icons.Filled.Add,
      contentDescription = "Add",
      tint = MaterialTheme.colors.fabIconColor
    )
  }
}
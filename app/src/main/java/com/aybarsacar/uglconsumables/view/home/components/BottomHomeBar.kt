package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.navigation.NavigationItem
import com.aybarsacar.uglconsumables.navigation.currentRoute
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme


@Composable
fun BottomHomeBar(
  navHostController: NavHostController,
  tabs: List<NavigationItem>
) {

  BottomAppBar(
    modifier = Modifier
      .graphicsLayer {
        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp).also { shape = it }
        clip = true
      },
    cutoutShape = RoundedCornerShape(50),
  ) {

    val currentRoute = navHostController.currentRoute()

    BottomNavigation {

      tabs.forEach { navigationItem ->
        BottomNavigationItem(
          selected = currentRoute == navigationItem.route,
          onClick = {
            if (currentRoute != navigationItem.route) {

              navHostController.navigate(navigationItem.route) {
                // pop up everything up to and including this route
                // so back button closes the whole screen
                popUpTo(navHostController.graph.findStartDestination().id) {
                  saveState = true
                }
                launchSingleTop = true    // doesn't launch if already selected
                restoreState = true       // restores state to avoid large stack
              }
            }
          },
          icon = { Icon(imageVector = navigationItem.icon, contentDescription = "Nav Icon") }
        )
      }
    }
  }
}


@Preview
@Composable
fun BottomHomeBarPreview() {
  BottomHomeBar(navHostController = rememberNavController(), listOf())
}

@Preview
@Composable
fun BottomHomeBarPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    BottomHomeBar(navHostController = rememberNavController(), listOf())
  }
}
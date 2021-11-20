package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aybarsacar.uglconsumables.view.Screen


@Composable
fun BottomHomeBar(
  navController: NavController
) {

  BottomAppBar(
    modifier = Modifier
      .padding(2.dp)
      .graphicsLayer {
        RoundedCornerShape(10.dp).also { shape = it }
        clip = true
      },
  ) {

    val screens = listOf(
      Screen.HomeScreen,
      Screen.ProfileScreen,
      Screen.SettingsScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(

    ) {
      screens.forEach { screen ->
        BottomNavigationItem(
          selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
          onClick = { navController.navigate(screen.route) },
          icon = { Icon(imageVector = screen.icon!!, contentDescription = "Nav Icon") }
        )
      }
    }
  }
}
package com.aybarsacar.uglconsumables.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.view.home.HomeScreen
import com.aybarsacar.uglconsumables.view.login_register.LoginRegisterScreen
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    setContent {
      UglConsumablesTheme {

        val navController = rememberNavController()

        NavHost(
          navController = navController,
          startDestination = if (true/*TODO - add auth logic*/) Screen.LoginRegisterScreen.route else Screen.HomeScreen.route
        ) {

          composable(
            route = Screen.LoginRegisterScreen.route
          ) {
            LoginRegisterScreen(navController = navController)
          }

          composable(
            route = Screen.HomeScreen.route
          ) {
            HomeScreen(navController = navController)
          }
        }
      }
    }
  }
}

package com.aybarsacar.uglconsumables.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.navigation.Screen
import com.aybarsacar.uglconsumables.navigation.createRouter
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.view.create_edit.CreateEditPage
import com.aybarsacar.uglconsumables.view.home.Home
import com.aybarsacar.uglconsumables.view.login_register.LoginRegisterScreen
import com.aybarsacar.uglconsumables.view.order_details.OrderDetailedScreen
import com.aybarsacar.uglconsumables.view.splash_screen.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      UglConsumablesTheme {

        val navController = rememberNavController()

        NavHost(
          navController = navController,
          startDestination = Screen.UglSplashScreen.route
        ) {

          composable(
            route = Screen.UglSplashScreen.route
          ) {
            SplashScreen(navController = navController)
          }

          composable(
            route = Screen.LoginRegister.route,
          ) {
            LoginRegisterScreen(navController = navController)
          }

          composable(
            route = Screen.Home.route
          ) {
            Home(
              createRouter { route ->
                navController.navigate(route)
              }
            )
          }

          composable(route = Screen.CreateEdit.route) {
            CreateEditPage(navController = navController)
          }

          composable(route = Screen.OrderDetailed.route) {
            val orderId = it.arguments?.getString("id")

            requireNotNull(orderId) {
              "Order Id cannot be null to navigate to this screen"
            }

            OrderDetailedScreen(orderId = orderId.toInt())
          }
        }
      }
    }
  }
}

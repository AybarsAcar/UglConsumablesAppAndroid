package com.aybarsacar.uglconsumables.view.splash_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.aybarsacar.uglconsumables.data.local.DataStoreRepository
import com.aybarsacar.uglconsumables.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect


/**
 * handles the datastore and local caching
 * if the user is already logged in sends them to the home screen
 */
@Composable
fun SplashScreen(
  navController: NavController,
) {

//  var account by remember { mutableStateOf(AccountDto()) }

  val context = LocalContext.current
  val dataStore = DataStoreRepository(context)

  LaunchedEffect(key1 = true) {

    delay(3000)

    dataStore.readUserDetails.collect {

      if (it.token.isEmpty()) {
        navController.navigate(Screen.LoginRegister.route)
      } else {
        navController.navigate(Screen.Home.route)
      }
    }
  }

  Text(text = "Splash Screen")

}
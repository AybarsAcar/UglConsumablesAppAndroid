package com.aybarsacar.uglconsumables.view.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.R
import com.aybarsacar.uglconsumables.data.local.DataStoreRepository
import com.aybarsacar.uglconsumables.navigation.Screen
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.loginRegisterScreenBackgroundColor
import kotlinx.coroutines.flow.collect


/**
 * handles the datastore and local caching
 * if the user is already logged in sends them to the home screen
 */
@Composable
fun SplashScreen(
  navController: NavController,
) {

  val context = LocalContext.current
  val dataStore = DataStoreRepository(context)

  LaunchedEffect(key1 = true) {

    dataStore.readUserDetails.collect {

      if (it.token.isEmpty()) {
        navController.navigate(Screen.LoginRegister.route) {
          popUpTo(Screen.LoginRegister.route) {
            inclusive = true
          }
        }
      } else {
        navController.navigate(Screen.Home.route) {
          popUpTo(Screen.Home.route) {
            inclusive = true
          }
        }
      }
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.loginRegisterScreenBackgroundColor),
    contentAlignment = Alignment.Center
  ) {
    Image(
      modifier = Modifier.size(300.dp),
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "Logo",
      colorFilter = ColorFilter.tint(Color.White)
    )
  }
}


@Preview
@Composable
fun SplashScreenPreview() {
  SplashScreen(navController = rememberNavController())
}

@Preview
@Composable
fun SplashScreenPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    SplashScreen(navController = rememberNavController())
  }
}
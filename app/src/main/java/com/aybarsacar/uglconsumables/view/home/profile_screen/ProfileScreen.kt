package com.aybarsacar.uglconsumables.view.home.profile_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aybarsacar.uglconsumables.navigation.Router
import com.aybarsacar.uglconsumables.navigation.Screen
import com.aybarsacar.uglconsumables.view.login_register.LoginRegisterViewModel


@Composable
fun ProfileScreen(
  externalRouter: Router,
  viewModel: LoginRegisterViewModel = hiltViewModel()
) {

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Profile Screen")

    Button(onClick = {
      viewModel.logout()
      externalRouter.navigateTo(Screen.LoginRegister.route)
    }) {
      Text(text = "Logout")
    }
  }
}
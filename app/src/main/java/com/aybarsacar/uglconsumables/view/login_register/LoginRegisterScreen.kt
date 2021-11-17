package com.aybarsacar.uglconsumables.view.login_register

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aybarsacar.uglconsumables.R
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.loginRegisterScreenBackgroundColor
import com.aybarsacar.uglconsumables.ui.theme.uglLogoColor
import com.aybarsacar.uglconsumables.view.login_register.components.LoginCard
import com.aybarsacar.uglconsumables.view.login_register.components.RegisterCard
import kotlinx.coroutines.launch


private enum class SelectedCard {
  LoginCard, RegisterCard
}


@ExperimentalAnimationApi
@Composable
fun LoginRegisterScreen(
  navController: NavController,
  viewModel: LoginRegisterViewModel = hiltViewModel()
) {

  // hide the system bars for the login screen
  /*val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.isStatusBarVisible = false
    systemUiController.isSystemBarsVisible = false
  }*/

  val scope = rememberCoroutineScope()
  val scaffoldState = rememberScaffoldState()
  val loginRegisterState by remember { viewModel.state }

  LaunchedEffect(key1 = loginRegisterState) {

    if (loginRegisterState.error.isNotEmpty()) {

      scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(
          message = loginRegisterState.error,
          actionLabel = "Ok"
        )
      }
    }
  }


  // show screen
  var selectedCard by remember { mutableStateOf(SelectedCard.LoginCard) }


  Box(
    modifier = Modifier.background(MaterialTheme.colors.loginRegisterScreenBackgroundColor)
  ) {

    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        modifier = Modifier
          .weight(1f)
          .size(200.dp),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        colorFilter = ColorFilter.tint(MaterialTheme.colors.uglLogoColor)
      )

      when (selectedCard) {

        SelectedCard.LoginCard -> {
          LoginCard(
            modifier = Modifier
              .weight(2f)
              .clip(shape = RoundedCornerShape(32.dp).copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize))
              .background(MaterialTheme.colors.surface),
            navController = navController,
            swapCard = { selectedCard = SelectedCard.RegisterCard }
          )
        }

        SelectedCard.RegisterCard -> {
          RegisterCard(
            modifier = Modifier
              .weight(2f)
              .clip(shape = RoundedCornerShape(32.dp).copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize))
              .background(MaterialTheme.colors.surface),
            navController = navController,
            viewModel = viewModel,
            swapCard = { selectedCard = SelectedCard.LoginCard }
          )
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {

  UglConsumablesTheme {
  }
}
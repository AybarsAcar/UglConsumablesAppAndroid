package com.aybarsacar.uglconsumables.view.login_register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.MaterialTheme
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
import com.aybarsacar.uglconsumables.util.Constants
import com.aybarsacar.uglconsumables.view.login_register.components.LoginCard
import com.aybarsacar.uglconsumables.view.login_register.components.RegisterCard
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalAnimationApi
@Composable
fun LoginRegisterScreen(
  navController: NavController,
  viewModel: LoginRegisterViewModel = hiltViewModel()
) {

  // hide the system bars for the login screen
  val systemUiController = rememberSystemUiController()
  systemUiController.isStatusBarVisible = false
  systemUiController.isSystemBarsVisible = false

  // show screen
  var isLoginScreenVisible by remember { mutableStateOf(true) }

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
          .size(200.dp)
          .clickable {
            isLoginScreenVisible = !isLoginScreenVisible
          },
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        colorFilter = ColorFilter.tint(MaterialTheme.colors.uglLogoColor)
      )


      AnimatedVisibility(
        modifier = Modifier.weight(2f),
        visible = isLoginScreenVisible,
        enter = slideInVertically(
          initialOffsetY = { it }, // it == fullWidth
          animationSpec = tween(
            durationMillis = Constants.ANIMATION_DURATION_IN_MILLIS,
            easing = LinearEasing // interpolator
          )
        ),
        exit = slideOutVertically(
          targetOffsetY = { it },
          animationSpec = tween(
            durationMillis = Constants.ANIMATION_DURATION_IN_MILLIS,
            easing = LinearEasing
          )
        )
      ) {
        LoginCard(
          modifier = Modifier
            .clip(shape = RoundedCornerShape(32.dp).copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize))
            .background(MaterialTheme.colors.surface),
          navController = navController,
          viewModel = viewModel
        )
      }


      AnimatedVisibility(
        modifier = Modifier.weight(2f),
        visible = !isLoginScreenVisible,
        enter = slideInVertically(
          initialOffsetY = { it }, // it == fullWidth
          animationSpec = tween(
            durationMillis = Constants.ANIMATION_DURATION_IN_MILLIS,
            easing = LinearEasing, // interpolator
          )
        ),
        exit = slideOutVertically(
          targetOffsetY = { it },
          animationSpec = tween(
            durationMillis = Constants.ANIMATION_DURATION_IN_MILLIS,
            easing = LinearEasing
          )
        )
      ) {
        RegisterCard(
          modifier = Modifier
            .clip(shape = RoundedCornerShape(32.dp).copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize))
            .background(MaterialTheme.colors.surface),
          navController = navController,
          viewModel = viewModel
        )
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
package com.aybarsacar.uglconsumables.view.login_register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aybarsacar.uglconsumables.R
import com.aybarsacar.uglconsumables.ui.theme.DarkBlue
import com.aybarsacar.uglconsumables.ui.theme.DefaultBlue
import com.aybarsacar.uglconsumables.ui.theme.LightBlue
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun LoginRegisterScreen(
  navController: NavController,
  viewModel: LoginRegisterViewModel = hiltViewModel()
) {

  // hide the system bars for the login screen
  val systemUiController = rememberSystemUiController()
  systemUiController.isStatusBarVisible = false
  systemUiController.isSystemBarsVisible = false

  // form state
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  // local state
  var isPasswordVisible by remember { mutableStateOf(false) }
  val isFormValid by derivedStateOf {
    username.isNotBlank() && password.length >= 8
  }

  Box(
    modifier = Modifier.background(
      Brush.verticalGradient(
        listOf(DarkBlue, DefaultBlue, LightBlue)
      )
    )
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
        colorFilter = ColorFilter.tint(Color.White)
      )

      Card(
        modifier = Modifier
          .weight(2f)
          .padding(8.dp),
        shape = RoundedCornerShape(32.dp)
      ) {

        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
        ) {

          Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome back!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
          )

          Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {

            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
              modifier = Modifier.fillMaxWidth(),
              value = username,
              onValueChange = {
                username = it
              },
              label = { Text(text = "Username") },
              trailingIcon = {
                if (username.isNotBlank()) {
                  IconButton(onClick = { username = ""; password = "" }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "")

                  }
                }
              },
              singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
              modifier = Modifier.fillMaxWidth(),
              value = password,
              onValueChange = { password = it },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
              visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
              label = { Text(text = "Password") },
              singleLine = true,
              trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                  Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "Password Toggle"
                  )
                }
              },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
              onClick = { /*TODO*/ },
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(16.dp),
              enabled = isFormValid
            ) {
              Text(text = "Login")
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign Up")
              }

              TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Forgot Password?", color = Color.Gray)

              }
            }
          }
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
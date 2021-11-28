package com.aybarsacar.uglconsumables.view.login_register.components


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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.aybarsacar.uglconsumables.util.noRippleClickable


@Composable
fun RegisterCard(
  modifier: Modifier = Modifier,
  viewModel: ViewModel,
  navController: NavController,
  swapCard: () -> Unit
) {

  val focusManager = LocalFocusManager.current

  // form state
  var username by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var passwordConfirm by remember { mutableStateOf("") }

  // local state
  var isPasswordVisible by remember { mutableStateOf(false) }
  val isFormValid by derivedStateOf {
    email.isNotBlank() && password.length >= 8
  }

  Card(
    modifier = modifier
      .noRippleClickable {
        focusManager.clearFocus()
      }
  ) {

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(32.dp)
    ) {

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
              IconButton(onClick = { username = "" }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = "")

              }
            }
          },
          singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
          modifier = Modifier.fillMaxWidth(),
          value = email,
          onValueChange = {
            email = it
          },
          label = { Text(text = "Email") },
          trailingIcon = {
            if (email.isNotBlank()) {
              IconButton(onClick = { email = "" }) {
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

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
          modifier = Modifier.fillMaxWidth(),
          value = passwordConfirm,
          onValueChange = { passwordConfirm = it },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
          visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
          label = { Text(text = "Confirm Password") },
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
          Text(text = "Register")
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Start,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "Already have an account?", color = Color.Gray)

          TextButton(onClick = { swapCard() }) {
            Text(
              text = "Login",
              style = TextStyle(textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
            )
          }
        }
      }
    }
  }
}
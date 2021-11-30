package com.aybarsacar.uglconsumables.view.create_edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aybarsacar.uglconsumables.data.remote.dto.AreaOfWorkFormValues
import com.aybarsacar.uglconsumables.navigation.Screen
import com.aybarsacar.uglconsumables.ui.theme.successBackgroundColor
import com.aybarsacar.uglconsumables.view.create_edit.viewmodel.CreateEditAreaOfWorkViewModel


@Composable
fun CreateEditAreaOfWorkTab(
  viewModel: CreateEditAreaOfWorkViewModel = hiltViewModel(),
  navController: NavController
) {

  val state = viewModel.state.value

  var serviceOrderId by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }

  val isFormValid by derivedStateOf {
    serviceOrderId.isNotBlank() && description.isNotBlank()
  }


  LaunchedEffect(key1 = state.success) {
    if (state.success) {
      navController.navigate(Screen.Home.route)
    }
  }


  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(32.dp)
  ) {

    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {

      OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = serviceOrderId,
        onValueChange = {
          serviceOrderId = it
        },
        label = { Text(text = "Sap Id") },
        trailingIcon = {
          if (serviceOrderId.isNotEmpty()) {
            IconButton(onClick = { serviceOrderId = "" }) {
              Icon(imageVector = Icons.Filled.Clear, contentDescription = "")

            }
          }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
      )

      Spacer(modifier = Modifier.height(8.dp))

      OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = description,
        onValueChange = {
          description = it
        },
        label = { Text(text = "Description") },
        trailingIcon = {
          if (description.isNotEmpty()) {
            IconButton(onClick = { description = "" }) {
              Icon(imageVector = Icons.Filled.Clear, contentDescription = "")

            }
          }
        },
        maxLines = 4
      )


      Spacer(modifier = Modifier.height(16.dp))

      Button(
        onClick = {
          viewModel.createAreaOfWork(AreaOfWorkFormValues(description, serviceOrderId.toInt()))
        },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.successBackgroundColor),
        modifier = Modifier
          .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        enabled = isFormValid
      ) {
        Text(text = "Create Work Area")
      }
    }
  }
}
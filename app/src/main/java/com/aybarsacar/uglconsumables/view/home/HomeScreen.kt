package com.aybarsacar.uglconsumables.view.home

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aybarsacar.uglconsumables.view.home.components.HomeAppBar


@Composable
fun HomeScreen() {

  Scaffold(
    topBar = {
      HomeAppBar()
    },
    floatingActionButton = {
      CreateNewOrderFab()
    },
  ) {
    // page content here
  }
}


@Composable
fun CreateNewOrderFab() {
  FloatingActionButton(
    onClick = {

    },

    backgroundColor = MaterialTheme.colors.primary

  ) {
    Icon(
      imageVector = Icons.Filled.Add,
      contentDescription = "Add",
      tint = Color.White
    )
  }
}
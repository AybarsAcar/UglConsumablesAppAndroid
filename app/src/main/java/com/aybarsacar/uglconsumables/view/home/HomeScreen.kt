package com.aybarsacar.uglconsumables.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aybarsacar.uglconsumables.view.home.components.HomeAppBar


@Composable
fun HomeScreen(
  navController: NavController,
  viewModel: HomeViewModel = hiltViewModel()
) {

  val state = viewModel.state.value

  Scaffold(
    topBar = {
      HomeAppBar()
    },
    floatingActionButton = {
      CreateNewOrderFab()
    },
  ) {
    // page content here

    LazyColumn(modifier = Modifier.fillMaxSize()) {

      items(state.consumables) { consumable ->

        Text(text = consumable.sapId.toString())

      }

    }
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
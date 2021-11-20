package com.aybarsacar.uglconsumables.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aybarsacar.uglconsumables.ui.theme.fabBackgroundColor
import com.aybarsacar.uglconsumables.ui.theme.fabIconColor
import com.aybarsacar.uglconsumables.view.home.components.ConsumableItem
import com.aybarsacar.uglconsumables.view.home.components.HomeAppBar


@ExperimentalMaterialApi
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

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(0.dp)
    ) {

      items(state.consumables) { consumable ->
        ConsumableItem(consumable = consumable, navigateToConsumable = {})
      }

    }
  }
}


@Composable
fun CreateNewOrderFab() {
  FloatingActionButton(
    onClick = {

    },

    backgroundColor = MaterialTheme.colors.fabBackgroundColor

  ) {
    Icon(
      imageVector = Icons.Filled.Add,
      contentDescription = "Add",
      tint = MaterialTheme.colors.fabIconColor
    )
  }
}
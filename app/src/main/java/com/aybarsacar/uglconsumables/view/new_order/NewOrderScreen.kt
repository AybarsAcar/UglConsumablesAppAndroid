package com.aybarsacar.uglconsumables.view.new_order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun NewOrderScreen(
  serviceOrderId: Int,
  viewModel: NewOrderViewModel = hiltViewModel()
) {

  val state = viewModel.state.value

  LaunchedEffect(key1 = true) {
    viewModel.getConsumablesForAreaOfWork(serviceOrderId)
  }

  when {
    state.isLoading -> {
      CircularProgressIndicator()
    }

    state.consumables.isNotEmpty() -> {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {

        items(state.consumables) { consumable ->
          Text(text = consumable.description, color = Color.White)
        }
      }
    }
  }
}
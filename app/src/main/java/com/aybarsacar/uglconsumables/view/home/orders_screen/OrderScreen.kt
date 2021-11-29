package com.aybarsacar.uglconsumables.view.home.orders_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aybarsacar.uglconsumables.navigation.Router
import com.aybarsacar.uglconsumables.navigation.Screen
import com.aybarsacar.uglconsumables.view.home.HomeViewModel
import com.aybarsacar.uglconsumables.view.home.components.EmptyContent
import com.aybarsacar.uglconsumables.view.home.components.OrderListItemCard
import com.aybarsacar.uglconsumables.view.home.components.Shimmer


@ExperimentalMaterialApi
@Composable
fun OrderScreen(
  externalRouter: Router,
  viewModel: HomeViewModel = hiltViewModel(),

  ) {

  val state = viewModel.state.value

  LaunchedEffect(key1 = true) {
    viewModel.getOrderListItems()
  }

  if (state.error != null) {
    EmptyContent(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    )
  }

  if (state.isLoading) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)
    ) {
      Shimmer()
    }
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(0.dp)
  ) {

    items(state.orderListItems) { order ->
      OrderListItemCard(orderListItem = order) {
        externalRouter.navigateTo(Screen.OrderDetailed.createRoute(it))
      }
    }
  }
}
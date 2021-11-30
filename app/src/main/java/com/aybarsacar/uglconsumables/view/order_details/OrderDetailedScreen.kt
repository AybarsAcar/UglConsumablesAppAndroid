package com.aybarsacar.uglconsumables.view.order_details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.ui.theme.*
import com.aybarsacar.uglconsumables.view.create_edit.BackAction
import com.aybarsacar.uglconsumables.view.home.components.CompletionIndicator
import com.aybarsacar.uglconsumables.view.order_details.composnents.OrderDetailsTop
import com.aybarsacar.uglconsumables.view.order_details.composnents.OrderItemCard


@Composable
fun OrderDetailedScreen(
  orderId: Int,
  navController: NavController,
  viewModel: OrderDetailedViewModel = hiltViewModel()
) {

  val state = viewModel.state.value


  LaunchedEffect(key1 = true) {
    viewModel.getOrderDetailed(orderId)
  }


  Scaffold(
    topBar = {
      TopAppBar(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.orderDetailsTopBackground,
        navigationIcon = {
          BackAction {
            navController.popBackStack()
          }
        },
        title = {
          Text(text = "Order $orderId", textAlign = TextAlign.Center, color = Color.White)
        },
        actions = {
          state.order?.let {
            CompletionIndicator(
              modifier = Modifier
                .size(16.dp),
              isCompleted = it.isClosed,
              successColor = if (isSystemInDarkTheme()) SuccessDark.toArgb() else SuccessLight.toArgb(),
              errorColor = if (isSystemInDarkTheme()) ErrorDark.toArgb() else ErrorLight.toArgb()
            )
          }
        }
      )
    }
  ) {

    when {
      state.isLoading -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
      }

      state.order != null -> {

        val currentOrder = state.order

        Column(
          modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.orderDetailsTopBackground)
        ) {

          OrderDetailsTop(
            serviceOrderId = currentOrder.serviceOrderId,
            areaOfWork = currentOrder.areaOfWorkDescription,
            createdBy = currentOrder.createdBy,
            createdAt = currentOrder.createdAt,
            comment = currentOrder.comment
          )

          OrderItemCard(
            modifier = Modifier.weight(1f),
            orderItems = currentOrder.orderItemDtos
          )
        }
      }
    }
  }
}


@Preview
@Composable
fun OrderDetailedScreenPreview() {
  OrderDetailedScreen(
    orderId = 1,
    navController = rememberNavController()
  )
}
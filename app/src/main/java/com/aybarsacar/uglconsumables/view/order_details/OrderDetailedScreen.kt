package com.aybarsacar.uglconsumables.view.order_details

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


@Composable
fun OrderDetailedScreen(
  orderId: Int
) {
  Box(contentAlignment = Alignment.Center) {

    Text(text = "OrderDetailedScreen $orderId", color = Color.White)

  }
}
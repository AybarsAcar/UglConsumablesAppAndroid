package com.aybarsacar.uglconsumables.view.order_details.composnents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.data.remote.dto.OrderItemDto
import com.aybarsacar.uglconsumables.util.noRippleClickable


@Composable
fun OrderItemCard(
  modifier: Modifier = Modifier,
  orderItems: List<OrderItemDto>
) {

  val focusManager = LocalFocusManager.current


  Card(
    modifier = modifier
      .fillMaxSize()
      .padding(top = 8.dp)
      .clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
      .background(MaterialTheme.colors.surface)
      .noRippleClickable {
        focusManager.clearFocus()
      }
  ) {

    LazyColumn(
      modifier = Modifier.padding(16.dp)
    ) {
      items(orderItems) {

        Row {
          Text(text = it.sapId.toString(), color = Color.White)
          Text(text = it.description, color = Color.White)
          Text(text = it.quantity.toString(), color = Color.White)
          Text(text = it.unitOfMeasure, color = Color.White)
          Text(text = if (it.isPrd) "PRD" else "B01", color = Color.White)
        }
      }
    }

  }

}


@Preview
@Composable
fun OrderItemCardPreview() {
  OrderItemCard(orderItems = emptyList())
}
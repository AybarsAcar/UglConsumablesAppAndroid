package com.aybarsacar.uglconsumables.view.order_details.composnents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme


@Composable
fun OrderDetailsTop(
  serviceOrderId: Int,
  areaOfWork: String,
  createdBy: String,
  createdAt: String,
  comment: String,
  modifier: Modifier = Modifier
) {

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(Color.Transparent)
      .padding(16.dp)
  ) {

    Text(
      text = serviceOrderId.toString(),
      color = Color.White,
      fontStyle = MaterialTheme.typography.h2.fontStyle,
      fontSize = 18.sp
    )

    Text(
      text = areaOfWork,
      color = Color.White,
      fontStyle = MaterialTheme.typography.h2.fontStyle,
      fontSize = 26.sp
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
      modifier = Modifier
        .padding(vertical = 12.dp)
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        text = createdBy,
        color = Color.White,
        fontStyle = MaterialTheme.typography.subtitle2.fontStyle
      )

      Text(
        text = createdAt,
        color = Color.White,
        fontStyle = MaterialTheme.typography.subtitle2.fontStyle
      )
    }

    Spacer(modifier = Modifier.height(12.dp))
    Divider()

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clickable {
          // TODO: Alert message with the comment
        }
        .padding(vertical = 12.dp)
    ) {
      Text(
        text = comment,
        color = Color.White,
        fontStyle = MaterialTheme.typography.body2.fontStyle,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )
    }

  }
}


@Preview
@Composable
fun OrderDetailsTopPreview() {
  OrderDetailsTop(
    60005,
    "Car Shop 1",
    "Aybars Acar",
    "27 Nov 2021",
    "This is created by and here is my comment..."
  )
}

@Preview
@Composable
fun OrderDetailsTopPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    OrderDetailsTop(
      60005,
      "Car Shop 1",
      "Aybars Acar",
      "27 Nov 2021",
      "This is created by and here is my comment..."
    )
  }
}
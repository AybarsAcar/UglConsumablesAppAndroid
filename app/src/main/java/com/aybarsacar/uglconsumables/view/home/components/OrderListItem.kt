package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.data.remote.dto.OrderListDto
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.itemBorderColor


@ExperimentalMaterialApi
@Composable
fun OrderListItem(
  orderListItem: OrderListDto,
  navigateToOrderDetails: (Int) -> Unit
) {

  var isExpanded by remember { mutableStateOf(false) }
  val rotationState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(2.dp)
      .animateContentSize(
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
      ),
    shape = RoundedCornerShape(2.dp),
    border = BorderStroke(1.dp, MaterialTheme.colors.itemBorderColor),
    elevation = 2.dp,
    onClick = {
      isExpanded = !isExpanded
    }
  ) {

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    ) {

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          modifier = Modifier.weight(6f),
          text = orderListItem.serviceOrderId.toString(),
          fontWeight = FontWeight.Bold,
          fontSize = MaterialTheme.typography.h6.fontSize,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )

        IconButton(
          modifier = Modifier
            .alpha(ContentAlpha.medium)
            .weight(1f)
            .rotate(rotationState),
          onClick = { isExpanded = !isExpanded }
        ) {
          Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "DropDown Icon")
        }
      }

      if (isExpanded) {

        Divider()

        Text(
          text = orderListItem.areaOfWorkDescription,
          fontSize = MaterialTheme.typography.subtitle1.fontSize,
          fontWeight = FontWeight.Normal,
          maxLines = 4,
          overflow = TextOverflow.Ellipsis
        )

        Text(
          text = orderListItem.createdBy,
          fontSize = MaterialTheme.typography.subtitle1.fontSize,
          fontWeight = FontWeight.Normal,
          maxLines = 4,
          overflow = TextOverflow.Ellipsis
        )

        Text(
          text = orderListItem.createdAt,
          fontSize = MaterialTheme.typography.subtitle1.fontSize,
          fontWeight = FontWeight.Normal,
          maxLines = 4,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun OrderListItemPreview() {
  OrderListItem(
    OrderListDto(1, 6000, "Car Shop 1", "aybarsacar", "10", false)
  ) {}
}

@ExperimentalMaterialApi
@Preview
@Composable
fun OrderListItemPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    OrderListItem(
      OrderListDto(1, 6000, "Car Shop 1", "aybarsacar", "10", false)
    ) {}
  }
}
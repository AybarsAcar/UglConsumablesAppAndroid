package com.aybarsacar.uglconsumables.view.create_edit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.data.remote.dto.AreaOfWorkDto
import com.aybarsacar.uglconsumables.ui.theme.successBackgroundColor


@Composable
fun MultiSelectorDialog(
  modifier: Modifier = Modifier,
  areaOfWorks: List<AreaOfWorkDto>,
  onAddItems: (List<Int>) -> Unit
) {

  var serviceOrders by remember { mutableStateOf(areaOfWorks.map { it.toAreaOfWorkSelectionItem() }) }

  Box(
    modifier = Modifier.background(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colors.surface),
  ) {
    LazyColumn(
      modifier = modifier
        .fillMaxSize()
        .padding(bottom = 50.dp)
    ) {

      items(serviceOrders.size) { i ->

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clickable {
              serviceOrders = serviceOrders.mapIndexed { j, serviceOrder ->
                if (i == j) {
                  serviceOrder.copy(isSelected = !serviceOrder.isSelected)
                } else {
                  serviceOrder
                }
              }
            }
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {

          Text(
            modifier = Modifier.weight(3f),
            text = serviceOrders[i].serviceOrder.toString(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )

          Text(
            modifier = Modifier.weight(6f),
            text = serviceOrders[i].description,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )

          if (serviceOrders[i].isSelected) {
            Icon(
              modifier = Modifier
                .size(20.dp)
                .weight(1f),
              imageVector = Icons.Default.Check,
              contentDescription = "Selected Icon",
              tint = MaterialTheme.colors.successBackgroundColor
            )
          }
        }
      }
    }

    Button(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomCenter),
      onClick = { onAddItems(serviceOrders.filter { it.isSelected }.map { it.serviceOrder }) }) {
      Text(text = "ADD")
    }
  }


}
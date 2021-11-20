package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableDto
import com.aybarsacar.uglconsumables.ui.theme.taskItemBackgroundColor
import com.aybarsacar.uglconsumables.ui.theme.taskItemTextColor


@ExperimentalMaterialApi
@Composable
fun ConsumableItem(
  consumable: ConsumableDto,
  navigateToConsumable: (consumableId: Int) -> Unit
) {

  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(2.dp),
    color = MaterialTheme.colors.taskItemBackgroundColor,
    shape = RoundedCornerShape(2.dp),
    elevation = 2.dp,
    border = BorderStroke(1.dp, Color.Black),
    onClick = {
      navigateToConsumable(consumable.id)
    }
  ) {

    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = consumable.sapId.toString(),
        color = MaterialTheme.colors.taskItemTextColor,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,
        maxLines = 1
      )

      Text(
        text = consumable.description,
        color = MaterialTheme.colors.taskItemTextColor,
        style = MaterialTheme.typography.subtitle1,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}
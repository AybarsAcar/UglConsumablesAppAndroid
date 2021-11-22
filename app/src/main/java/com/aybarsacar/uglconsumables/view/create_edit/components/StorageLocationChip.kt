package com.aybarsacar.uglconsumables.view.create_edit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.ui.theme.addEditImageBackground


@Composable
fun StorageLocationChip(
  location: String,
  modifier: Modifier = Modifier,
  isSelected: Boolean,
  onSelected: () -> Unit
) {
  Surface(
    modifier = modifier,
    elevation = 8.dp,
    shape = MaterialTheme.shapes.medium,
    color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.addEditImageBackground
  ) {

    Row(modifier = Modifier.clickable { onSelected() }) {

      Text(
        text = location,
        style = MaterialTheme.typography.body2,
        color = Color.White,
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp),
        textAlign = TextAlign.Center
      )
    }
  }
}
package com.aybarsacar.uglconsumables.view.create_edit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.addEditImageBackground


@Composable
fun AddEditImage() {

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(240.dp)
      .clip(RoundedCornerShape(6.dp))
      .background(color = MaterialTheme.colors.addEditImageBackground),
    contentAlignment = Alignment.BottomEnd
  ) {

    IconButton(onClick = { /*TODO*/ }) {
      Icon(
        imageVector = Icons.Default.PhotoCamera,
        contentDescription = "Add Photo",
        tint = Color.White
      )
    }
  }

}


@Preview
@Composable
fun AddEditImagePreview() {
  AddEditImage()
}

@Preview
@Composable
fun AddEditImagePreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    AddEditImage()
  }
}
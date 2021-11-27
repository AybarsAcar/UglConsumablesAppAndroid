package com.aybarsacar.uglconsumables.view.create_edit.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.addEditImageBackground
import com.aybarsacar.uglconsumables.util.Constants
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@ExperimentalPermissionsApi
@Composable
fun AddEditImage(
  modifier: Modifier = Modifier,
  imageUri: Uri,
  openCamera: () -> Unit,
  openGallery: () -> Unit
) {

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(240.dp)
      .clip(RoundedCornerShape(6.dp))
      .background(color = MaterialTheme.colors.addEditImageBackground)
  ) {

    if (imageUri != Constants.EMPTY_IMAGE_URI) {
      Image(
        modifier = Modifier.fillMaxSize(),
        painter = rememberImagePainter(imageUri),
        contentDescription = "Captured image"
      )
    }

    IconButton(
      modifier = Modifier.align(Alignment.BottomEnd),
      onClick = {
        openCamera()
      }) {
      Icon(
        imageVector = Icons.Default.PhotoCamera,
        contentDescription = "Add Photo",
        tint = Color.White
      )
    }

    IconButton(
      modifier = Modifier.align(Alignment.BottomStart),
      onClick = {
        openGallery()
      }) {
      Icon(
        imageVector = Icons.Default.PhotoAlbum,
        contentDescription = "Go to Album",
        tint = Color.White
      )
    }
  }
}


@ExperimentalPermissionsApi
@Preview
@Composable
fun AddEditImagePreview() {
//  AddEditImage(imageUri = Constants.EMPTY_IMAGE_URI) {}
}

@ExperimentalPermissionsApi
@Preview
@Composable
fun AddEditImagePreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
//    AddEditImage(imageUri = Constants.EMPTY_IMAGE_URI) {}
  }
}
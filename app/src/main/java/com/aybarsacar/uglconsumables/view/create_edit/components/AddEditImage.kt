package com.aybarsacar.uglconsumables.view.create_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.addEditImageBackground
import com.aybarsacar.uglconsumables.util.Constants
import com.aybarsacar.uglconsumables.util.camera.CameraCapture
import com.aybarsacar.uglconsumables.util.gallery.GallerySelect
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@ExperimentalPermissionsApi
@Composable
fun AddEditImage(modifier: Modifier = Modifier) {

  // TODO: May update the logic of launching the camera - may potentially make it full screen

  val isCameraOpen by remember { mutableStateOf(false) }

  if (!isCameraOpen) {
    Column {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(240.dp)
          .clip(RoundedCornerShape(6.dp))
          .background(color = MaterialTheme.colors.addEditImageBackground),
        contentAlignment = Alignment.BottomEnd
      ) {

        MainContent(modifier = Modifier.fillMaxSize())

//        IconButton(onClick = { TODO: FIX
//          isCameraOpen = true
//        }) {
//          Icon(
//            imageVector = Icons.Default.PhotoCamera,
//            contentDescription = "Add Photo",
//            tint = Color.White
//          )
//        }
      }
    }
  } else {
  }

}


@ExperimentalPermissionsApi
@Composable
fun MainContent(modifier: Modifier = Modifier) {

  var imageUri by remember { mutableStateOf(Constants.EMPTY_IMAGE_URI) }

  if (imageUri != Constants.EMPTY_IMAGE_URI) {

    Box(modifier = modifier) {
      Image(
        modifier = Modifier.fillMaxSize(),
        painter = rememberImagePainter(imageUri),
        contentDescription = "Captured image"
      )

      Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
        imageUri = Constants.EMPTY_IMAGE_URI
      }) {
        Text(text = "Remove image")
      }
    }

  } else {

    var showGallerySelect by remember { mutableStateOf(false) }

    if (showGallerySelect) {

      GallerySelect(
        modifier = modifier,
        onImageUri = { uri ->
          showGallerySelect = false
          imageUri = uri
        }
      )

    } else {

      Box(modifier = modifier) {
        CameraCapture(
          modifier = modifier,
          onImageFile = { file ->
            imageUri = file.toUri()
          }
        )
        Button(
          modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(4.dp),
          onClick = {
            showGallerySelect = true
          }
        ) {
          Text("Select from Gallery")
        }
      }

    }
  }
}


@ExperimentalPermissionsApi
@Preview
@Composable
fun AddEditImagePreview() {
  AddEditImage()
}

@ExperimentalPermissionsApi
@Preview
@Composable
fun AddEditImagePreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    AddEditImage()
  }
}
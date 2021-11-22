package com.aybarsacar.uglconsumables.view.create_edit.components

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.addEditImageBackground
import com.aybarsacar.uglconsumables.util.Permission
import com.aybarsacar.uglconsumables.util.camera.CameraPreview
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@ExperimentalPermissionsApi
@Composable
fun AddEditImage(modifier: Modifier = Modifier) {

  // TODO: May update the logic of launching the camera - may potentially make it full screen

  var isCameraOpen by remember { mutableStateOf(false) }

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

        IconButton(onClick = {
          isCameraOpen = true
        }) {
          Icon(
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = "Add Photo",
            tint = Color.White
          )
        }
      }
    }
  } else {
    MainContent(modifier = Modifier.fillMaxSize())
  }

}


@ExperimentalPermissionsApi
@Composable
fun MainContent(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  Permission(
    permission = Manifest.permission.CAMERA,
    rationale = "Permission is required to access the camera",
    permissionNotAvailableContent = {

      // TODO: Change the Text to snackbars

      Column(modifier) {
        Text("Permission denied")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
          context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            this.data = Uri.fromParts("package", context.packageName, null)
          })
        }) {
          Text("Open Settings")
        }
      }
    }
  ) {
    CameraPreview()
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
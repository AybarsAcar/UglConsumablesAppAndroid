package com.aybarsacar.uglconsumables.util.gallery

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.util.Constants
import com.aybarsacar.uglconsumables.util.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@ExperimentalPermissionsApi
@Composable
fun GallerySelect(
  modifier: Modifier = Modifier,
  onImageUri: (Uri) -> Unit = {}
) {

  val context = LocalContext.current

  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent(),
    onResult = { uri: Uri? ->
      onImageUri(uri ?: Constants.EMPTY_IMAGE_URI)
    }
  )

  @Composable
  fun LaunchGallery() {
    // wrap it in SideEffect because this is causing the side effect of launching another Activity
    SideEffect {
      launcher.launch("image/*")
    }
  }

  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

    Permission(
      permission = Manifest.permission.ACCESS_MEDIA_LOCATION,
      rationale = "Permission is required to access the gallery.",
      permissionNotAvailableContent = {

        Column(modifier) {
          Text("Access denied.")
          Spacer(modifier = Modifier.height(8.dp))
          Row {
            Button(
              modifier = Modifier.padding(4.dp),
              onClick = {
                context.startActivity(
                  Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                  }
                )
              }
            ) {
              Text("Open Settings")
            }
            // If they don't want to grant permissions, this button will result in going back
            Button(
              modifier = Modifier.padding(4.dp),
              onClick = {
                onImageUri(Constants.EMPTY_IMAGE_URI)
              }
            ) {
              Text("Use Camera")
            }
          }
        }
      }
    ) {
      LaunchGallery()
    }

  } else {
    LaunchGallery()
  }
}
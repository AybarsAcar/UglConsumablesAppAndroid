package com.aybarsacar.uglconsumables.util.camera

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@ExperimentalPermissionsApi
@Composable
fun MainContent(
  modifier: Modifier = Modifier,
  selectImage: (Uri) -> Unit,
  onBackClicked: () -> Unit
) {

  Box(modifier = modifier) {
    CameraCapture(
      modifier = modifier,
      onImageFile = { file ->
        selectImage(file.toUri())
      },
      onBackClicked = onBackClicked
    )
  }
}

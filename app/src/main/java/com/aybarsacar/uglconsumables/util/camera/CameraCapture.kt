package com.aybarsacar.uglconsumables.util.camera

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.util.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch
import java.io.File


@ExperimentalPermissionsApi
@Composable
fun CameraCapture(
  modifier: Modifier = Modifier,
  cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
  onImageFile: (File) -> Unit = {},
  onBackClicked: () -> Unit = {}
) {

  // TODO: add functionality - currently only to update the UI
  var isFlashEnabled by remember { mutableStateOf(false) }
  val camera: Camera? = null

  val context = LocalContext.current

  Permission(
    permission = Manifest.permission.CAMERA,
    rationale = "Permission is required to access the camera.",
    permissionNotAvailableContent = {
      Column(modifier) {
        Text("Permission denied!")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
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
      }
    }
  ) {

    Box(modifier = modifier) {

      val lifecycleOwner = LocalLifecycleOwner.current

      val coroutineScope = rememberCoroutineScope()

      var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }

      val imageCaptureUseCase by remember {
        mutableStateOf(
          ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY).build()
        )
      }


      LaunchedEffect(key1 = previewUseCase) {

        val cameraProvider = context.getCameraProvider()

        try {
          // Must unbind the use-cases before rebinding them.
          cameraProvider.unbindAll()
          cameraProvider.bindToLifecycle(
            lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
          )
        } catch (e: Exception) {
          Log.e("CameraCapture", "Failed to bind camera use cases", e)
        }
      }


      Box {

        CameraPreview(modifier = Modifier.fillMaxSize()) {
          previewUseCase = it
        }

        IconButton(
          modifier = Modifier.align(Alignment.TopStart),
          onClick = {
            onBackClicked()
          }) {
          Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
        }

        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .padding(8.dp)
            .align(Alignment.BottomCenter)
        ) {

          IconButton(
            onClick = {
              camera?.let {
                println("TOGGLE FLASH")
                if (it.cameraInfo.hasFlashUnit()) {
                  // toggle camera flash
                  isFlashEnabled = !isFlashEnabled
                  it.cameraControl.enableTorch(isFlashEnabled)
                }
              }
            }) {
            Icon(
              modifier = Modifier.size(32.dp),
              imageVector = if (isFlashEnabled) Icons.Default.FlashOn else Icons.Default.FlashOff,
              contentDescription = "Camera Flash"
            )
          }

          CapturePictureButton(
            modifier = Modifier.size(50.dp),
            onClick = {
              coroutineScope.launch {
                imageCaptureUseCase.takePicture(context.executor).let {
                  onImageFile(it)
                }
              }
            }
          )

          IconButton(
            onClick = {
              // TODO
            }) {
            Icon(
              modifier = Modifier.size(32.dp),
              imageVector = Icons.Default.RotateRight,
              contentDescription = "Front Camera"
            )
          }
        }
      }
    }
  }
}

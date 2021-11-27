package com.aybarsacar.uglconsumables.util.camera

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
  onImageFile: (File) -> Unit = {}
) {


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

        CapturePictureButton(
          modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
            .align(Alignment.BottomCenter),
          onClick = {
            coroutineScope.launch {
              imageCaptureUseCase.takePicture(context.executor).let {
                onImageFile(it)
              }
            }
          }
        )
      }
    }
  }
}
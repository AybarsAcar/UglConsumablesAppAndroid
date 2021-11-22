package com.aybarsacar.uglconsumables.util.camera

import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch


/**
 * this is the camera preview
 * the image processed by the device camera
 * encapsulates the PreviewView in a Composable
 */
@Composable
fun CameraPreview(
  modifier: Modifier = Modifier,
  scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
  cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
) {

  val coroutineScope = rememberCoroutineScope() // coroutine scope of the current Composable context
  val lifecycleOwner = LocalLifecycleOwner.current

  // compatibility wrapper for legacy views - wraps the PreviewView
  AndroidView(
    modifier = modifier,
    factory = { context ->

      // Render CameraX PreviewView and configure
      val previewView = PreviewView(context).apply {
        this.scaleType = scaleType
        this.layoutParams = ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT
        )
      }

      // CameraX Preview UseCase
      val previewUseCase = Preview.Builder()
        .build()
        .also {
          it.setSurfaceProvider(previewView.surfaceProvider)
        }

      coroutineScope.launch {
        val cameraProvider = context.getCameraProvider()

        try {
          // must unbind the use-cases before rebinding them
          cameraProvider.unbindAll()

          cameraProvider.bindToLifecycle(
            lifecycleOwner, cameraSelector, previewUseCase
          )

        } catch (e: Exception) {
          Log.e("Camera Preview", "Use case binding failed")
        }
      }

      previewView
    }
  )
}
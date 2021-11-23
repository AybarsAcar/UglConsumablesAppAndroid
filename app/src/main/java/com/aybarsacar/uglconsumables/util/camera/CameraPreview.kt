package com.aybarsacar.uglconsumables.util.camera

import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


/**
 * this is the camera preview
 * the image processed by the device camera
 * encapsulates the PreviewView in a Composable
 */
@Composable
fun CameraPreview(
  modifier: Modifier = Modifier,
  scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
  onUseCase: (UseCase) -> Unit
) {

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
      onUseCase(
        Preview.Builder()
          .build()
          .also {
            it.setSurfaceProvider(previewView.surfaceProvider)
          }
      )

      previewView
    }
  )
}
package com.aybarsacar.uglconsumables.util.camera

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

/**
 * face analyser built into CameraX
 * pass into the ImageCaptureBuilder to detect faces
 */
class FaceAnalyzer() : ImageAnalysis.Analyzer {

  @SuppressLint("UnsafeOptInUsageError")
  override fun analyze(image: ImageProxy) {

    val imagePic = image.image
    imagePic?.close()
  }
}
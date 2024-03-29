package com.aybarsacar.uglconsumables.util.camera

import android.content.Context
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * wrap the future in a coroutine
 */
suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->

  ProcessCameraProvider.getInstance(this).also { future ->
    future.addListener({ continuation.resume(future.get()) }, executor)
  }

}


val Context.executor: Executor
  get() = ContextCompat.getMainExecutor(this)


suspend fun ImageCapture.takePicture(executor: Executor): File {

  val photoFile = withContext(Dispatchers.IO) {
    kotlin.runCatching {
      File.createTempFile("image", "jpg")
    }.getOrElse { e ->
      Log.e("Take Picture", "Failed to create temporary file", e)
      File("/dev/null")
    }
  }

  return suspendCoroutine { continuation ->

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
      override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
        continuation.resume(photoFile)
      }

      override fun onError(exception: ImageCaptureException) {
        Log.e("TakePicture", "Image capture failed", exception)
        continuation.resumeWithException(exception)
      }
    })
  }
}
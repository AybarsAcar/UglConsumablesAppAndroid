package com.aybarsacar.uglconsumables.util.camera

import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor
import kotlin.coroutines.resume
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
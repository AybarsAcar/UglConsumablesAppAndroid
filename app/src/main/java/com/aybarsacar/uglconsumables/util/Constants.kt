package com.aybarsacar.uglconsumables.util

import android.net.Uri

object Constants {

  const val BASE_URL = "http://10.0.2.2:5000/api/"

  const val ANIMATION_DURATION_IN_MILLIS = 300

  val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")
}
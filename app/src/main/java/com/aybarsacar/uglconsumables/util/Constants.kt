package com.aybarsacar.uglconsumables.util

import android.net.Uri

object Constants {

  const val BASE_URL = "http://10.0.2.2:5000/api/"

  const val ANIMATION_DURATION_IN_MILLIS = 300

  val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

  const val PREFERENCES_NAME = "ugl_consumables_app_preferences"
  const val PREFERENCES_USERNAME = "ugl_consumable_username"
  const val PREFERENCES_EMAIL = "ugl_consumable_email"
  const val PREFERENCES_TOKEN = "ugl_consumable_token"
  const val PREFERENCES_DEPARTMENT = "ugl_consumable_department"
}
package com.aybarsacar.uglconsumables.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      UglConsumablesTheme {

        Button(onClick = { }) {

          Text(text = "My Button")

        }

      }
    }
  }
}

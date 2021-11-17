package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aybarsacar.uglconsumables.ui.theme.DarkBlue


@Composable
fun HomeAppBar() {
  TopAppBar(
    title = { Text(text = "UGL Consumables", color = Color.White) },
    backgroundColor = DarkBlue
  )
}
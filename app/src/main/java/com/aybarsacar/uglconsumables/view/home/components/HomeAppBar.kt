package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.aybarsacar.uglconsumables.ui.theme.DarkBlue
import com.aybarsacar.uglconsumables.ui.theme.uglLogoColor


@Composable
fun HomeAppBar() {
  TopAppBar(
    title = { Text(text = "UGL Consumables", color = MaterialTheme.colors.uglLogoColor) },
    backgroundColor = DarkBlue
  )
}
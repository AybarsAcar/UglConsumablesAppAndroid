package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.R
import com.aybarsacar.uglconsumables.ui.theme.topAppBarContentColor
import com.aybarsacar.uglconsumables.ui.theme.uglLogoColor


@Composable
fun HomeAppBar() {
  TopAppBar(
    title = {
      Image(
        modifier = Modifier
          .size(80.dp),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        colorFilter = ColorFilter.tint(MaterialTheme.colors.uglLogoColor)
      )
    },
    actions = {
      SearchAction { }
    }
  )
}

@Composable
fun SearchAction(
  onSearchClicked: () -> Unit
) {
  IconButton(
    onClick = { onSearchClicked() }
  ) {

    Icon(
      imageVector = Icons.Filled.Search,
      contentDescription = "Search Icon",
      tint = MaterialTheme.colors.topAppBarContentColor
    )

  }
}

package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.aybarsacar.uglconsumables.R
import com.aybarsacar.uglconsumables.ui.theme.EMPTY_CONTENT_ICON_SIZE
import com.aybarsacar.uglconsumables.ui.theme.MediumGray
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme


@Composable
fun EmptyContent(
  modifier: Modifier = Modifier
) {

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Icon(
      modifier = Modifier
        .size(EMPTY_CONTENT_ICON_SIZE)
        .alpha(0.6f),
      painter = painterResource(id = R.drawable.ic_baseline_sentiment_very_dissatisfied_24),
      contentDescription = "Empty icon",
      tint = MediumGray
    )

    Text(
      text = "Oops.. No Content is available",
      color = MediumGray,
      fontWeight = FontWeight.Bold,
      fontSize = MaterialTheme.typography.h6.fontSize
    )

  }
}


@Preview
@Composable
fun EmptyContentPreview() {
  EmptyContent(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.background)
  )
}

@Preview
@Composable
fun EmptyContentPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    EmptyContent(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    )
  }
}
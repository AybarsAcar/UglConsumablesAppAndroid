package com.aybarsacar.uglconsumables.view.home.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme


@Composable
fun Shimmer() {

  val shimmerColors = listOf(
    Color.LightGray.copy(alpha = 0.6f),
    Color.LightGray.copy(alpha = 0.2f),
    Color.LightGray.copy(alpha = 0.6f)
  )

  val transition = rememberInfiniteTransition()

  val translateAnimation =
    transition.animateFloat(
      initialValue = 0f,
      targetValue = 1000f,
      animationSpec = infiniteRepeatable(
        animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
      )
    )

  val brush = Brush.linearGradient(
    colors = shimmerColors,
    start = Offset.Zero,
    end = Offset(x = translateAnimation.value, y = translateAnimation.value)
  )

  ShimmerGridItem(brush = brush)
}


@Composable
fun ShimmerGridItem(brush: Brush) {
  Spacer(
    modifier = Modifier
      .fillMaxWidth()
      .size(50.dp)
      .background(brush = brush)
      .clip(RoundedCornerShape(2.dp))
  )
}


@Preview
@Composable
fun ShimmerPreview() {
  Shimmer()
}

@Preview
@Composable
fun ShimmerPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    Shimmer()
  }
}
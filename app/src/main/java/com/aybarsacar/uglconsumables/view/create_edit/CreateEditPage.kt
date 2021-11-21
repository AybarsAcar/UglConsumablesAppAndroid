package com.aybarsacar.uglconsumables.view.create_edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun CreateEditPage() {

  val tabData = listOf(
    "Area of Work" to Icons.Default.Apartment,
    "Consumable" to Icons.Default.Architecture
  )

  val pagerState = rememberPagerState(
    pageCount = tabData.size,
    initialOffscreenLimit = 2,
    infiniteLoop = false,
    initialPage = 1,
  )

  val tabIndex = pagerState.currentPage
  val coroutineScope = rememberCoroutineScope()

  Column {
    TabRow(
      selectedTabIndex = tabIndex,
//      indicator = { tabPositions ->
//        TabRowDefaults.Indicator(
//          modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
//        )
//      }
    ) {

      tabData.forEachIndexed { index, pair ->
        Tab(selected = tabIndex == index, onClick = {
          coroutineScope.launch {
            pagerState.animateScrollToPage(index)
          }
        }, text = {
          Text(text = pair.first)
        }, icon = {
          Icon(imageVector = pair.second, contentDescription = null)
        })
      }
    }

    HorizontalPager(
      state = pagerState,
      modifier = Modifier.weight(1f)
    ) { index ->
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = tabData[index].first,
        )
      }
    }
  }
}
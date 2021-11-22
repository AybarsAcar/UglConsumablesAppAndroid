package com.aybarsacar.uglconsumables.view.create_edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch


@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun CreateEditPage(
  navController: NavController
) {

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


  Scaffold(
    topBar = {
      TopAppBar(
        navigationIcon = {
          IconButton(onClick = { }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
          }
        },
        title = {
          Text(text = "Create")
        },
      )
    }
  ) {
    Column {
      TabRow(
        selectedTabIndex = tabIndex,
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

          when (tabData[index].first) {

            "Area of Work" -> {
              CreateEditAreaOfWorkTab()
            }

            "Consumable" -> {
              CreateEditConsumableTab()
            }
          }
        }
      }
    }
  }
}


@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Preview
@Composable
fun CreateEditPagePreview() {
  CreateEditPage(navController = rememberNavController())
}

@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Preview
@Composable
fun CreateEditPagePreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    CreateEditPage(navController = rememberNavController())
  }
}
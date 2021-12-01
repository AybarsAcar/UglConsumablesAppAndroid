package com.aybarsacar.uglconsumables.view.home.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aybarsacar.uglconsumables.navigation.Router
import com.aybarsacar.uglconsumables.navigation.Screen
import com.aybarsacar.uglconsumables.view.home.HomeViewModel
import com.aybarsacar.uglconsumables.view.home.components.AreaOfWorkItem
import com.aybarsacar.uglconsumables.view.home.components.EmptyContent
import com.aybarsacar.uglconsumables.view.home.components.Shimmer


@ExperimentalMaterialApi
@Composable
fun HomeScreen(
  externalRouter: Router,
  viewModel: HomeViewModel = hiltViewModel()
) {
  val state = viewModel.state.value

  if (state.error != null) {
    EmptyContent(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    )
  }

  if (state.isLoading) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)
    ) {
      Shimmer()
    }
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(0.dp)
  ) {

    items(state.areaOfWorks) { areaOfWork ->
      AreaOfWorkItem(areaOfWork = areaOfWork, navigateToServiceOrderConsumables = {
        externalRouter.navigateTo(Screen.NewOrderConsumableSelection.createRoute(it))
      })
    }
  }
}


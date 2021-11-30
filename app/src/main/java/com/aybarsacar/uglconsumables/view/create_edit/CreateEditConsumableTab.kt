package com.aybarsacar.uglconsumables.view.create_edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableFormValues
import com.aybarsacar.uglconsumables.navigation.Screen
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.successBackgroundColor
import com.aybarsacar.uglconsumables.util.Constants
import com.aybarsacar.uglconsumables.util.camera.MainContent
import com.aybarsacar.uglconsumables.util.gallery.GallerySelect
import com.aybarsacar.uglconsumables.view.create_edit.components.AddEditImage
import com.aybarsacar.uglconsumables.view.create_edit.components.MultiSelectorDialog
import com.aybarsacar.uglconsumables.view.create_edit.components.MyDropdown
import com.aybarsacar.uglconsumables.view.create_edit.components.StorageLocationChip
import com.aybarsacar.uglconsumables.view.create_edit.viewmodel.CreateEditConsumableViewModel
import com.aybarsacar.uglconsumables.view.home.HomeViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi


enum class UnitOfMeasure(val value: String) {
  EA("EA"), BOX("BOX"), M("M"), L("L")
}


@ExperimentalPermissionsApi
@Composable
fun CreateEditConsumableTab(
  homeViewModel: HomeViewModel = hiltViewModel(),
  viewModel: CreateEditConsumableViewModel = hiltViewModel(),
  navController: NavController
) {
  val homeState = homeViewModel.state.value
  val state = viewModel.state.value

  val scrollState = rememberScrollState()

  var isServiceOrderDialogShowing by remember { mutableStateOf(false) }

  // form values
  var sapId by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  var unitOfMeasure by remember { mutableStateOf(UnitOfMeasure.EA) }
  var isPrd by remember { mutableStateOf(false) }
  var serviceOrderIds by remember { mutableStateOf(emptyList<Int>()) }

  val isFormValid by derivedStateOf {
    sapId.isNotBlank() && description.isNotBlank()
  }


  // image selection values
  var imageUri by remember { mutableStateOf(Constants.EMPTY_IMAGE_URI) }
  var isInCameraMode by remember { mutableStateOf(false) }
  var showGallerySelect by remember { mutableStateOf(false) }


  LaunchedEffect(key1 = state.success) {
    if (state.success) {
      navController.navigate(Screen.Home.route)
    }
  }



  when {

    isInCameraMode -> {

      MainContent(
        selectImage = {
          imageUri = it
          isInCameraMode = false
        },
        onBackClicked = {
          isInCameraMode = false
        }
      )
    }

    showGallerySelect -> {
      GallerySelect(
        modifier = Modifier.fillMaxSize(),
        onImageUri = { uri ->
          showGallerySelect = false
          imageUri = uri
        }
      )
    }

    else -> {

      if (isServiceOrderDialogShowing) {

        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
        ) {
          MultiSelectorDialog(areaOfWorks = homeState.areaOfWorks) { selectedServiceOrders ->
            isServiceOrderDialogShowing = false
            serviceOrderIds = selectedServiceOrders
          }
        }

      } else {

        Column(
          modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(32.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {

          AddEditImage(
            imageUri = imageUri,
            openCamera = {
              isInCameraMode = true
            },
            openGallery = {
              showGallerySelect = true
            }
          )

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = sapId,
            onValueChange = {
              sapId = it
            },
            label = { Text(text = "Sap Id") },
            trailingIcon = {
              if (sapId.isNotEmpty()) {
                IconButton(onClick = { sapId = "" }) {
                  Icon(imageVector = Icons.Filled.Clear, contentDescription = "")

                }
              }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
          )

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = {
              description = it
            },
            label = { Text(text = "Description") },
            trailingIcon = {
              if (description.isNotEmpty()) {
                IconButton(onClick = { description = "" }) {
                  Icon(imageVector = Icons.Filled.Clear, contentDescription = "")

                }
              }
            },
            maxLines = 4
          )

          Spacer(modifier = Modifier.height(12.dp))

          Row(modifier = Modifier.fillMaxWidth()) {
            StorageLocationChip(
              location = "BO1",
              modifier = Modifier
                .padding(end = 4.dp)
                .weight(1f),
              isSelected = !isPrd
            ) {
              isPrd = false
            }
            StorageLocationChip(
              location = "PRD",
              modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f),
              isSelected = isPrd
            ) {
              isPrd = true
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          MyDropdown(
            options = UnitOfMeasure.values().map { it.value }
          ) {
            unitOfMeasure = UnitOfMeasure.valueOf(it)
          }

          Spacer(modifier = Modifier.height(12.dp))

          Button(
            onClick = {
              //TODO: Create consumable
              isServiceOrderDialogShowing = true
            },
            modifier = Modifier.fillMaxWidth(),
          ) {
            Text(text = "Add to Service Order")
          }

          Spacer(modifier = Modifier.height(16.dp))

          Button(
            onClick = {
              // Create Consumable
              viewModel.createConsumable(
                ConsumableFormValues(
                  sapId.toInt(),
                  description,
                  unitOfMeasure.value,
                  isPrd,
                  serviceOrderIds
                )
              )
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.successBackgroundColor),
            modifier = Modifier
              .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            enabled = isFormValid
          ) {
            Text(text = "Create Consumable")
          }
        }
      }
    }
  }
}


@ExperimentalPermissionsApi
@Preview
@Composable
fun CreateEditConsumableTabPreview() {
  CreateEditConsumableTab(navController = rememberNavController())
}

@ExperimentalPermissionsApi
@Preview
@Composable
fun CreateEditConsumableTabPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    CreateEditConsumableTab(navController = rememberNavController())
  }
}
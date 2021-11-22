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
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.successBackgroundColor
import com.aybarsacar.uglconsumables.view.create_edit.components.AddEditImage
import com.aybarsacar.uglconsumables.view.create_edit.components.MyDropdown
import com.aybarsacar.uglconsumables.view.create_edit.components.StorageLocationChip


enum class UnitOfMeasure(val value: String) {
  EA("EA"), BOX("BOX"), M("M"), L("L")
}


@Composable
fun CreateEditConsumableTab() {

  var sapId by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  var unitOfMeasure by remember { mutableStateOf(UnitOfMeasure.EA) }
  var isPrd by remember { mutableStateOf(false) }
  var serviceOrderIds by remember { mutableStateOf(emptyList<Int>()) }


  val scrollState = rememberScrollState()


  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {

    AddEditImage()

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
      options = UnitOfMeasure.values().map { it.value }) {

    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      onClick = {
        //TODO: Create consumable
      },
      colors = ButtonDefaults.buttonColors(MaterialTheme.colors.successBackgroundColor),
      modifier = Modifier
        .fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      enabled = true, // TODO: add form check
    ) {
      Text(text = "Create Consumable")
    }
  }
}


@Preview
@Composable
fun CreateEditConsumableTabPreview() {
  CreateEditConsumableTab()
}

@Preview
@Composable
fun CreateEditConsumableTabPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    CreateEditConsumableTab()
  }
}
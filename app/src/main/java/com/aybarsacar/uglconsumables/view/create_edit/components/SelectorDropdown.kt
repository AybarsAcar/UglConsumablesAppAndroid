package com.aybarsacar.uglconsumables.view.create_edit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme


@Composable
fun MyDropdown(
  options: List<String>,
  handleSelection: (String) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }
  var selectedText by remember { mutableStateOf("") }

  var dropDownWidth by remember { mutableStateOf(0) }

  val icon = if (expanded)
    Icons.Filled.ArrowDropUp
  else
    Icons.Filled.ArrowDropDown


  Column {
    OutlinedTextField(
      value = selectedText,
      onValueChange = { selectedText = it },
      modifier = Modifier
        .fillMaxWidth()
        .onSizeChanged {
          dropDownWidth = it.width
        },
      label = { Text("Unit of Measure") },
      trailingIcon = {
        Icon(icon, "Expand Icon", Modifier.clickable { expanded = !expanded })
      }
    )
    DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
      modifier = Modifier
        .width(with(LocalDensity.current) { dropDownWidth.toDp() })
    ) {
      options.forEach { label ->
        DropdownMenuItem(onClick = {
          selectedText = label
          handleSelection(selectedText)
          expanded = false
        }) {
          Text(text = label)
        }
      }
    }
  }
}


@Preview
@Composable
fun MyDropdownPreview() {
  MyDropdown(listOf("Item1", "Item2", "Item3")) {}
}

@Preview
@Composable
fun MyDropdownPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    MyDropdown(listOf("Item1", "Item2", "Item3")) {}
  }
}
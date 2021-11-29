package com.aybarsacar.uglconsumables.view.create_edit.viewmodell


data class CreateEditState(
  val success: Boolean = false,
  val isLoading: Boolean = false,
  val error: String = ""
)
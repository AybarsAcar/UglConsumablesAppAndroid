package com.aybarsacar.uglconsumables.view.create_edit.viewmodel


data class CreateEditState(
  val success: Boolean = false,
  val isLoading: Boolean = false,
  val error: String = ""
)
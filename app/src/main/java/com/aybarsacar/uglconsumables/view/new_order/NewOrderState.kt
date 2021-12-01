package com.aybarsacar.uglconsumables.view.new_order

import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableDto


data class NewOrderState(
  val isLoading: Boolean = false,
  val consumables: List<ConsumableDto> = emptyList(),
  val error: String = ""
)
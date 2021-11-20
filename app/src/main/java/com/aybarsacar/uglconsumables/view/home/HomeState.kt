package com.aybarsacar.uglconsumables.view.home

import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableDto


data class HomeState(
  val consumables: List<ConsumableDto> = emptyList(),
  val isLoading: Boolean = false,
  val error: String? = null
)

package com.aybarsacar.uglconsumables.view.home

import com.aybarsacar.uglconsumables.data.remote.dto.AreaOfWorkDto
import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableDto
import com.aybarsacar.uglconsumables.data.remote.dto.OrderListDto


data class HomeState(
  val consumables: List<ConsumableDto> = emptyList(),
  val areaOfWorks: List<AreaOfWorkDto> = emptyList(),
  val orderListItems: List<OrderListDto> = emptyList(),
  val isLoading: Boolean = false,
  val error: String? = null
)

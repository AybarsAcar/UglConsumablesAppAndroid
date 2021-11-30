package com.aybarsacar.uglconsumables.view.order_details

import com.aybarsacar.uglconsumables.data.remote.dto.OrderDetailedDto


data class OrderDetailedState(
  val isLoading: Boolean = false,
  val order: OrderDetailedDto? = null,
  val error: String = ""
)

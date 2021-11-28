package com.aybarsacar.uglconsumables.domain.models

import java.time.LocalDate


data class OrderListItem(
  val id: Int,
  val serviceOrderId: Int,
  val areaOfWorkDescription: String,
  val createdBy: String,
  val createdAt: LocalDate,
  val isClosed: Boolean
)

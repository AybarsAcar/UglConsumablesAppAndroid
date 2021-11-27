package com.aybarsacar.uglconsumables.data.remote.dto


data class OrderListDto(
  val id: Int,
  val serviceOrderId: Int,
  val areaOfWorkDescription: String,
  val createdBy: String,
  val createdAt: String,
  val isClosed: Boolean
)

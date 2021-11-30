package com.aybarsacar.uglconsumables.data.remote.dto


import com.google.gson.annotations.SerializedName


data class OrderDetailedDto(
  @SerializedName("areaOfWorkDescription")
  val areaOfWorkDescription: String,
  @SerializedName("comment")
  val comment: String,
  @SerializedName("createdAt")
  val createdAt: String,
  @SerializedName("createdBy")
  val createdBy: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("isClosed")
  val isClosed: Boolean,
  @SerializedName("orderItems")
  val orderItemDtos: List<OrderItemDto>,
  @SerializedName("serviceOrderId")
  val serviceOrderId: Int
)
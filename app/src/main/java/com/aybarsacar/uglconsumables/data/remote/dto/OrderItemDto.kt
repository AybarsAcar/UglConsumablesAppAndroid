package com.aybarsacar.uglconsumables.data.remote.dto


import com.google.gson.annotations.SerializedName


data class OrderItemDto(
  @SerializedName("description")
  val description: String,
  @SerializedName("isPrd")
  val isPrd: Boolean,
  @SerializedName("quantity")
  val quantity: Int,
  @SerializedName("sapId")
  val sapId: Int,
  @SerializedName("unitOfMeasure")
  val unitOfMeasure: String
)
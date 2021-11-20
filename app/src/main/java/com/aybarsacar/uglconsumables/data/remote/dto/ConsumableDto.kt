package com.aybarsacar.uglconsumables.data.remote.dto


import com.google.gson.annotations.SerializedName


data class ConsumableDto(
  @SerializedName("areaOfWorks")
  val areaOfWorks: List<AreaOfWorkDto>,
  @SerializedName("description")
  val description: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("isPrd")
  val isPrd: Boolean,
  @SerializedName("quantity")
  val quantity: Int,
  @SerializedName("sapId")
  val sapId: Int,
  @SerializedName("unitOfMeasure")
  val unitOfMeasure: String
)
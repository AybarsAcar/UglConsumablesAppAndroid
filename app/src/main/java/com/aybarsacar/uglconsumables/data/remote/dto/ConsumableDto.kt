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


data class ConsumableFormValues(
  val sapId: Int = -1,
  val description: String = "",
  val unitOfMeasure: String = "",
  val isPrd: Boolean = false,
  val serviceOrderIds: List<Int> = emptyList(), // id of the Area of Work's service order
)
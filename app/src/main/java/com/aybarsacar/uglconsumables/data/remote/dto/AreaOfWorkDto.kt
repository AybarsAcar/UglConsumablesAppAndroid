package com.aybarsacar.uglconsumables.data.remote.dto


import com.google.gson.annotations.SerializedName


data class AreaOfWorkDto(
  @SerializedName("description")
  val description: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("serviceOrder")
  val serviceOrder: Int
)

data class AreaOfWorkFormValues(
  @SerializedName("description")
  val description: String,
  @SerializedName("serviceOrder")
  val serviceOrder: Int
)

package com.aybarsacar.uglconsumables.data.remote.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.aybarsacar.uglconsumables.domain.models.OrderListItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class OrderListDto(
  val id: Int,
  val serviceOrderId: Int,
  val areaOfWorkDescription: String,
  val createdBy: String,
  val createdAt: String,
  val isClosed: Boolean
) {

  @RequiresApi(Build.VERSION_CODES.O)
  fun toOrderListItem(): OrderListItem {

    return OrderListItem(
      id = id,
      serviceOrderId = serviceOrderId,
      areaOfWorkDescription = areaOfWorkDescription,
      createdBy = createdBy,
      createdAt = LocalDate.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME),
      isClosed = isClosed
    )
  }
}
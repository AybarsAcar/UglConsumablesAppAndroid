package com.aybarsacar.uglconsumables.domain.repository

import com.aybarsacar.uglconsumables.data.remote.dto.OrderDetailedDto
import com.aybarsacar.uglconsumables.data.remote.dto.OrderListDto
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow


interface OrderRepository {

  fun listOrders(): Flow<Resource<List<OrderListDto>>>

  fun getOrderDetails(id: Int): Flow<Resource<OrderDetailedDto>>
}
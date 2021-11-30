package com.aybarsacar.uglconsumables.data.repository

import com.aybarsacar.uglconsumables.data.remote.UglConsumablesAppApi
import com.aybarsacar.uglconsumables.data.remote.dto.OrderDetailedDto
import com.aybarsacar.uglconsumables.data.remote.dto.OrderListDto
import com.aybarsacar.uglconsumables.domain.repository.OrderRepository
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class OrderRepositoryImpl @Inject constructor(private val _api: UglConsumablesAppApi) : OrderRepository {

  override fun listOrders(): Flow<Resource<List<OrderListDto>>> = flow {

    emit(Resource.Loading<List<OrderListDto>>())

    // TODO: implement caching

    try {

      val orderListItems = _api.getOrders()

      emit(Resource.Success<List<OrderListDto>>(orderListItems))

    } catch (e: HttpException) {

      emit(Resource.Error<List<OrderListDto>>(message = e.localizedMessage ?: "Oops, something went wrong..."))

    } catch (e: IOException) {

      emit(
        Resource.Error<List<OrderListDto>>(
          message = e.localizedMessage ?: "Couldn't reach the server. Please check your internet connection."
        )
      )
    }
  }


  override fun getOrderDetails(id: Int): Flow<Resource<OrderDetailedDto>> = flow {
    emit(Resource.Loading<OrderDetailedDto>())

    // TODO: implement caching

    try {

      val order = _api.getOrderDetails(id)

      emit(Resource.Success<OrderDetailedDto>(order))

    } catch (e: HttpException) {

      emit(Resource.Error<OrderDetailedDto>(message = e.localizedMessage ?: "Oops, something went wrong..."))

    } catch (e: IOException) {

      emit(
        Resource.Error<OrderDetailedDto>(
          message = e.localizedMessage ?: "Couldn't reach the server. Please check your internet connection."
        )
      )
    }
  }
}
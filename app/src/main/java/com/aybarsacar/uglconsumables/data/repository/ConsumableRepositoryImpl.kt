package com.aybarsacar.uglconsumables.data.repository

import com.aybarsacar.uglconsumables.data.remote.UglConsumablesAppApi
import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableDto
import com.aybarsacar.uglconsumables.domain.repository.ConsumableRepository
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class ConsumableRepositoryImpl @Inject constructor(private val _api: UglConsumablesAppApi) : ConsumableRepository {

  override fun getConsumables(serviceOrderId: Int?): Flow<Resource<List<ConsumableDto>>> = flow {

    emit(Resource.Loading<List<ConsumableDto>>())

    // TODO: Implement Caching

    try {

      val consumables = _api.getConsumables()

      emit(Resource.Success<List<ConsumableDto>>(consumables))

    } catch (e: HttpException) {

      emit(Resource.Error<List<ConsumableDto>>(message = e.localizedMessage ?: "Oops, something went wrong..."))

    } catch (e: IOException) {

      emit(
        Resource.Error<List<ConsumableDto>>(
          message = e.localizedMessage ?: "Couldn't reach the server. Please check your internet connection."
        )
      )
    }
  }
}
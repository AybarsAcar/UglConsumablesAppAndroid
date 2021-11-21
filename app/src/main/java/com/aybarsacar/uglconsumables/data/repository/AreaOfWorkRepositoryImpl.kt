package com.aybarsacar.uglconsumables.data.repository

import com.aybarsacar.uglconsumables.data.remote.UglConsumablesAppApi
import com.aybarsacar.uglconsumables.data.remote.dto.AreaOfWorkDto
import com.aybarsacar.uglconsumables.domain.repository.AreaOfWorkRepository
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class AreaOfWorkRepositoryImpl @Inject constructor(private val _api: UglConsumablesAppApi) : AreaOfWorkRepository {

  override fun getAreaOfWorks(): Flow<Resource<List<AreaOfWorkDto>>> = flow {

    emit(Resource.Loading<List<AreaOfWorkDto>>())

    // TODO: Implement caching

    try {

      val areaOfWorks = _api.getAreaOfWorks()

      // TODO: Implement mapping to - create models to pass around in the view

      emit(Resource.Success<List<AreaOfWorkDto>>(areaOfWorks))

    } catch (e: HttpException) {

      emit(Resource.Error<List<AreaOfWorkDto>>(message = e.localizedMessage ?: "Oops, something went wrong..."))

    } catch (e: IOException) {

      emit(
        Resource.Error<List<AreaOfWorkDto>>(
          message = e.localizedMessage ?: "Couldn't reach the server. Please check your internet connection."
        )
      )
    }
  }
}
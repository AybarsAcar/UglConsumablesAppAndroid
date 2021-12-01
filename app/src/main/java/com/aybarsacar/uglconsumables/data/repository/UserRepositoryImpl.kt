package com.aybarsacar.uglconsumables.data.repository

import com.aybarsacar.uglconsumables.data.remote.UglConsumablesAppApi
import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto
import com.aybarsacar.uglconsumables.data.remote.dto.LoginAccountDetails
import com.aybarsacar.uglconsumables.data.remote.dto.RegisterAccountDetails
import com.aybarsacar.uglconsumables.domain.repository.UserRepository
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * implementation class of the UserRepository
 */
class UserRepositoryImpl @Inject constructor(private val _api: UglConsumablesAppApi) : UserRepository {

  override suspend fun getUsers(): List<AccountDto> {
    return _api.getUsers()
  }

  override suspend fun getUserByEmail(email: String): AccountDto {
    return _api.getUserByEmail(email)
  }

  override fun getCurrentUser(token: String): Flow<Resource<AccountDto>> = flow {

    try {
      emit(Resource.Loading<AccountDto>())

      val response = _api.getCurrentUser("Bearer $token")

      emit(Resource.Success<AccountDto>(response))

    } catch (e: HttpException) {

      emit(Resource.Error<AccountDto>(e.localizedMessage ?: "AN unexpected error ocurred"))
    } catch (e: IOException) {

      emit(Resource.Error<AccountDto>("Could not reach server. Check your internet connection"))
    }

  }

  override fun register(registerAccountDetails: RegisterAccountDetails): Flow<Resource<AccountDto>> = flow {

    try {
      emit(Resource.Loading<AccountDto>())

      val response = _api.register(registerAccountDetails)

      // TODO cache the currently logged in user

      emit(Resource.Success<AccountDto>(response))

    } catch (e: HttpException) {

      emit(Resource.Error<AccountDto>(e.localizedMessage ?: "An unexpected error occurred"))

    } catch (e: IOException) {

      emit(Resource.Error<AccountDto>("Could not reach server. Check your internet connection"))
    }

  }


  override fun login(loginAccountDetails: LoginAccountDetails): Flow<Resource<AccountDto>> = flow {

    try {

      emit(Resource.Loading<AccountDto>())

      val response = _api.login(loginAccountDetails)

      // TODO cache the currently logged in user

      emit(Resource.Success<AccountDto>(response))


    } catch (e: HttpException) {

      emit(Resource.Error<AccountDto>(e.localizedMessage ?: "An unexpected error occurred"))

    } catch (e: IOException) {

      emit(Resource.Error<AccountDto>("Could not reach server. Check your internet connection"))
    }
  }
}
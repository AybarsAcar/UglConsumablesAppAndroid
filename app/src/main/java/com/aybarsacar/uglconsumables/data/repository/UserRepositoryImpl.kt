package com.aybarsacar.uglconsumables.data.repository

import com.aybarsacar.uglconsumables.data.remote.UserApi
import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto
import com.aybarsacar.uglconsumables.data.remote.dto.LoginAccountDetails
import com.aybarsacar.uglconsumables.data.remote.dto.RegisterAccountDetails
import com.aybarsacar.uglconsumables.domain.repository.UserRepository
import javax.inject.Inject


/**
 * implementation class of the UserRepository
 */
class UserRepositoryImpl @Inject constructor(private val _api: UserApi) : UserRepository {

  override suspend fun getUsers(): List<AccountDto> {
    return _api.getUsers()
  }

  override suspend fun getUserByEmail(email: String): AccountDto {
    return _api.getUserByEmail(email)
  }

  override suspend fun getCurrentUser(): AccountDto {
    return _api.getCurrentUser()
  }

  override suspend fun register(registerAccountDetails: RegisterAccountDetails): AccountDto {
    return _api.register(registerAccountDetails)
  }

  override suspend fun login(loginAccountDetails: LoginAccountDetails): AccountDto {
    return _api.login(loginAccountDetails)
  }
}
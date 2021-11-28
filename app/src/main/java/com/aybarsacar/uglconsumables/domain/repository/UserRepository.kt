package com.aybarsacar.uglconsumables.domain.repository

import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto
import com.aybarsacar.uglconsumables.data.remote.dto.LoginAccountDetails
import com.aybarsacar.uglconsumables.data.remote.dto.RegisterAccountDetails
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow


/**
 * user repository methods posted to the API
 */
interface UserRepository {

  suspend fun getUsers(): List<AccountDto>

  suspend fun getUserByEmail(email: String): AccountDto

  suspend fun getCurrentUser(): AccountDto

  fun register(registerAccountDetails: RegisterAccountDetails): Flow<Resource<AccountDto>>

  fun login(loginAccountDetails: LoginAccountDetails): Flow<Resource<AccountDto>>

}
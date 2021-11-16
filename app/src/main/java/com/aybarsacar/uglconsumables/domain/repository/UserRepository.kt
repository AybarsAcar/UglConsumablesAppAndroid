package com.aybarsacar.uglconsumables.domain.repository

import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto
import com.aybarsacar.uglconsumables.data.remote.dto.LoginAccountDetails
import com.aybarsacar.uglconsumables.data.remote.dto.RegisterAccountDetails


/**
 * user repository methods posted to the API
 */
interface UserRepository {

  suspend fun getUsers(): List<AccountDto>

  suspend fun getUserByEmail(email: String): AccountDto

  suspend fun getCurrentUser(): AccountDto

  suspend fun register(registerAccountDetails: RegisterAccountDetails): AccountDto

  suspend fun login(loginAccountDetails: LoginAccountDetails): AccountDto

}
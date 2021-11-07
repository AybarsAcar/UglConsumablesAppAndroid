package com.aybarsacar.uglconsumables.domain.repository

import com.aybarsacar.uglconsumables.data.remote.dto.UserDto


/**
 * user repository methods posted to the API
 */
interface UserRepository {

  suspend fun getUsers(): List<UserDto>

  suspend fun getUserByUsername(id: String): UserDto

}
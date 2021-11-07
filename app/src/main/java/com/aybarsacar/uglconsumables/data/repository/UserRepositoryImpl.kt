package com.aybarsacar.uglconsumables.data.repository

import com.aybarsacar.uglconsumables.data.remote.dto.UserDto
import com.aybarsacar.uglconsumables.domain.repository.UserRepository


/**
 * implementation class of the UserRepository
 */
class UserRepositoryImpl : UserRepository{
  override suspend fun getUsers(): List<UserDto> {
    TODO("Not yet implemented")
  }

  override suspend fun getUserByUsername(id: String): UserDto {
    TODO("Not yet implemented")
  }
}
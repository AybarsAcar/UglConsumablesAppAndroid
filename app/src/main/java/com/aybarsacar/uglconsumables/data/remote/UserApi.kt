package com.aybarsacar.uglconsumables.data.remote

import com.aybarsacar.uglconsumables.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * API calls to the server for the user
 */
interface UserApi {

  @GET("User/email/{email}")
  suspend fun getUserByUsername(@Path("email") email: String): UserDto

}
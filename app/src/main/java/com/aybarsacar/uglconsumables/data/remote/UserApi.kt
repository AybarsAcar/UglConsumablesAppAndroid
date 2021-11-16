package com.aybarsacar.uglconsumables.data.remote

import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto
import com.aybarsacar.uglconsumables.data.remote.dto.LoginAccountDetails
import com.aybarsacar.uglconsumables.data.remote.dto.RegisterAccountDetails
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * API calls to the server for the user
 */
interface UserApi {

  @GET("User")
  suspend fun getUsers(): List<AccountDto>


  @GET("User/email/{email}")
  suspend fun getUserByEmail(@Path("email") email: String): AccountDto

  /**
   * returns the logged in user
   */
  @POST("Account")
  suspend fun getCurrentUser(): AccountDto


  @POST("Account/register")
  suspend fun register(@Body registerAccountDetails: RegisterAccountDetails): AccountDto


  @POST("Account/login")
  suspend fun login(@Body loginAccountDetails: LoginAccountDetails): AccountDto

}
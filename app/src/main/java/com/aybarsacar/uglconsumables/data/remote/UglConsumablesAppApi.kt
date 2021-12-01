package com.aybarsacar.uglconsumables.data.remote

import com.aybarsacar.uglconsumables.data.remote.dto.*
import retrofit2.http.*


/**
 * API calls to the server for the user
 */
interface UglConsumablesAppApi {

  @GET("User")
  suspend fun getUsers(): List<AccountDto>

  @GET("User/email/{email}")
  suspend fun getUserByEmail(@Path("email") email: String): AccountDto

  /**
   * returns the currently logged in user
   * make sure to pass the token as "Bearer $token"
   */
  @POST("Account")
  suspend fun getCurrentUser(@Header("Authorization") token: String): AccountDto

  @POST("Account/register")
  suspend fun register(@Body registerAccountDetails: RegisterAccountDetails): AccountDto

  @POST("Account/login")
  suspend fun login(@Body loginAccountDetails: LoginAccountDetails): AccountDto


  @GET("Consumable")
  suspend fun getConsumables(@Query("serviceOrderId") serviceOrderId: Int? = null): List<ConsumableDto>

  @POST("Consumable")
  suspend fun createConsumable(@Body consumableFormValues: ConsumableFormValues)


  @GET("AreaOfWork")
  suspend fun getAreaOfWorks(): List<AreaOfWorkDto>

  @POST("AreaOfWork")
  suspend fun createAreaOfWork(@Body areaOfWorkFormValues: AreaOfWorkFormValues)


  @GET("Order")
  suspend fun getOrders(@Query("serviceOrderId") serviceOrderId: Int? = null): List<OrderListDto>

  @GET("Order/{id}")
  suspend fun getOrderDetails(@Path("id") id: Int): OrderDetailedDto
}
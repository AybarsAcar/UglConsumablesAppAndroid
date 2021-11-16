package com.aybarsacar.uglconsumables.data.remote.dto

import com.google.gson.annotations.SerializedName


/**
 * user object retrieved from the UGL API
 */
data class AccountDto(
  @SerializedName("username") val username: String,
  @SerializedName("email") val email: String,
  @SerializedName("token") var token: String,
  @SerializedName("department") val department: String
)


data class RegisterAccountDetails(
  @SerializedName("username") val username: String,
  @SerializedName("email") val email: String,
  @SerializedName("password") var password: String,
)


data class LoginAccountDetails(
  @SerializedName("email") val email: String,
  @SerializedName("password") var password: String,
)
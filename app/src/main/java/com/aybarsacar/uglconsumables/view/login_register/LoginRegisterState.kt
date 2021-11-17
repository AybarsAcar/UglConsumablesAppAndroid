package com.aybarsacar.uglconsumables.view.login_register

import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto


data class LoginRegisterState(
  val isLoading: Boolean = false,
  val account: AccountDto? = null,
  val error: String = ""
)
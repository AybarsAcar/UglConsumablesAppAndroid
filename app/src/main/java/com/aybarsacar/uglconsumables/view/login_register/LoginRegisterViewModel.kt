package com.aybarsacar.uglconsumables.view.login_register

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.data.remote.dto.LoginAccountDetails
import com.aybarsacar.uglconsumables.domain.repository.UserRepository
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LoginRegisterViewModel @Inject constructor(private val _userRepository: UserRepository) : ViewModel() {

  private var _token by mutableStateOf("")

  private val _state = mutableStateOf(LoginRegisterState())
  val state: State<LoginRegisterState> = _state


  fun login(loginAccountDetails: LoginAccountDetails) {

    _userRepository.login(loginAccountDetails).onEach { result ->

      when (result) {
        is Resource.Success -> {

          _state.value = LoginRegisterState(account = result.data)

          result.data?.let { _token = it.token }
        }

        is Resource.Loading -> {
          _state.value = LoginRegisterState(isLoading = true)
        }

        is Resource.Error -> {
          val message = result.message ?: "An unexpected error occurred"

          _state.value = LoginRegisterState(error = message)
        }
      }

    }.launchIn(viewModelScope)

  }
}



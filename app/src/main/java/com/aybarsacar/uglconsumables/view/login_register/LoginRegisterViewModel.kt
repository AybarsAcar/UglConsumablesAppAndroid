package com.aybarsacar.uglconsumables.view.login_register

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.data.remote.dto.AccountDto
import com.aybarsacar.uglconsumables.data.remote.dto.LoginAccountDetails
import com.aybarsacar.uglconsumables.domain.repository.UserRepository
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class LoginRegisterViewModel @Inject constructor(private val _userRepository: UserRepository) : ViewModel() {

  private var _token by mutableStateOf("")

  private val _state = mutableStateOf(LoginRegisterState())
  val state: State<LoginRegisterState> = _state


  fun login(loginAccountDetails: LoginAccountDetails) {

    loginFlow(loginAccountDetails).onEach { result ->

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


  private fun loginFlow(loginAccountDetails: LoginAccountDetails) = flow {

    try {

      emit(Resource.Loading<AccountDto>())

      val response = _userRepository.login(loginAccountDetails)

      emit(Resource.Success<AccountDto>(response))

    } catch (e: HttpException) {

      emit(Resource.Error<AccountDto>(e.localizedMessage ?: "An unexpected error occurred"))

    } catch (e: IOException) {

      emit(Resource.Error<AccountDto>("Could not reach server. Check your internet connection"))
    }
  }
}



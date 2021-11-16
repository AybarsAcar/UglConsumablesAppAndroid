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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class LoginRegisterViewModel @Inject constructor(private val _userRepository: UserRepository) : ViewModel() {


  var account by mutableStateOf(AccountDto("", "", "", ""))


  private val _state = mutableStateOf(LoginRegisterState())
  val state: State<LoginRegisterState> = _state


  fun login(loginAccountDetails: LoginAccountDetails) {

    viewModelScope.launch {

      try {
        val response = _userRepository.login(loginAccountDetails)

        account = response

      } catch (e: HttpException) {

        println(e.localizedMessage)

      } catch (e: IOException) {

        println("Unexpected error occurred")

      }


    }

  }


//  fun login(loginAccountDetails: LoginAccountDetails): Flow<Resource<AccountDto>> = flow {
//    println("EXECUTED")
//    try {
//
//      emit(Resource.Loading<AccountDto>())
//
//      // execute the request
//      val accountDto = _userRepository.login(loginAccountDetails)
//
//
//      // TODO: store the token in user prefs local database so the logged in state is persisted between sessions
//
//      emit(Resource.Success<AccountDto>(accountDto))
//
//
//    } catch (e: HttpException) {
//      // response code that does not start with 2
//      emit(Resource.Error<AccountDto>(e.localizedMessage ?: "An unexpected error occurred"))
//    } catch (e: IOException) {
//      // i.e. the user doesn't have internet permit connection, or server is offline
//      emit(Resource.Error<AccountDto>("Could not reach server. Check your internet connection"))
//    }
//  }
}
package com.aybarsacar.uglconsumables.view.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.domain.use_case.GetConsumablesUseCase
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val _getConsumablesUseCase: GetConsumablesUseCase) : ViewModel() {

  private val _state = mutableStateOf(HomeState())
  val state: State<HomeState> = _state

  init {
    getConsumables()
  }

  private fun getConsumables() {

    _getConsumablesUseCase().onEach {

      when (it) {

        is Resource.Success -> {
          _state.value = HomeState(consumables = it.data ?: emptyList())
        }

        is Resource.Error -> {
          _state.value = HomeState(error = it.message)
        }

        is Resource.Loading -> {
          _state.value = HomeState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }
}
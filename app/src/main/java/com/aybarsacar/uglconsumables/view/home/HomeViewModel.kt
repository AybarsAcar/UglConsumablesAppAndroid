package com.aybarsacar.uglconsumables.view.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.domain.repository.AreaOfWorkRepository
import com.aybarsacar.uglconsumables.domain.repository.ConsumableRepository
import com.aybarsacar.uglconsumables.domain.repository.OrderRepository
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
  private val _consumableRepository: ConsumableRepository,
  private val _areaOfWorkRepository: AreaOfWorkRepository,
  private val _orderRepository: OrderRepository
) : ViewModel() {

  private val _state = mutableStateOf(HomeState())
  val state: State<HomeState> = _state

  init {
    getAreaOfWorks()
  }

  fun getConsumables() {

    _consumableRepository.getConsumables().onEach {

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


  fun getAreaOfWorks() {

    _areaOfWorkRepository.getAreaOfWorks().onEach {

      when (it) {

        is Resource.Success -> {
          _state.value = HomeState(areaOfWorks = it.data ?: emptyList())
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


  fun getOrderListItems() {
    _orderRepository.listOrders().onEach {

      when (it) {

        is Resource.Success -> {
          _state.value = HomeState(orderListItems = it.data ?: emptyList())
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
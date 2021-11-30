package com.aybarsacar.uglconsumables.view.order_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.domain.repository.OrderRepository
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class OrderDetailedViewModel @Inject constructor(
  private val _orderRepository: OrderRepository,
  _savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val _state = mutableStateOf(OrderDetailedState())
  val state: MutableState<OrderDetailedState> get() = _state

  init {
//    _savedStateHandle.get<>()
  }

  fun getOrderDetailed(id: Int) {

    _orderRepository.getOrderDetails(id).onEach { result ->

      when (result) {
        is Resource.Success -> {

          _state.value = OrderDetailedState(order = result.data)
        }

        is Resource.Loading -> {
          _state.value = OrderDetailedState(isLoading = true)
        }

        is Resource.Error -> {
          val message = result.message ?: "An unexpected error occurred"

          _state.value = OrderDetailedState(error = message)
        }
      }
    }.launchIn(viewModelScope)
  }

}
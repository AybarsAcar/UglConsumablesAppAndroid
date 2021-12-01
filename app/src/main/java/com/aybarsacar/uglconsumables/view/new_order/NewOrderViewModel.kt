package com.aybarsacar.uglconsumables.view.new_order

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.domain.repository.ConsumableRepository
import com.aybarsacar.uglconsumables.domain.repository.OrderRepository
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class NewOrderViewModel @Inject constructor(
  private val _consumableRepository: ConsumableRepository,
  private val _orderRepository: OrderRepository
) : ViewModel() {

  private val _state = mutableStateOf(NewOrderState())
  val state: State<NewOrderState> = _state


  fun getConsumablesForAreaOfWork(serviceOrderId: Int) {

    _consumableRepository.getConsumables(serviceOrderId).onEach {

      when (it) {

        is Resource.Success -> {
          _state.value = NewOrderState(consumables = it.data ?: emptyList())
        }

        is Resource.Error -> {
          _state.value = NewOrderState(error = it.message ?: "Unexpected error occurred")
        }

        is Resource.Loading -> {
          _state.value = NewOrderState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }

}
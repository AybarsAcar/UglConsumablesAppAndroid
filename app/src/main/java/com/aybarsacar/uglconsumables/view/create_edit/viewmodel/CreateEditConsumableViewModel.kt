package com.aybarsacar.uglconsumables.view.create_edit.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableFormValues
import com.aybarsacar.uglconsumables.domain.repository.ConsumableRepository
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CreateEditConsumableViewModel @Inject constructor(
  private val _consumableRepository: ConsumableRepository
) : ViewModel() {

  private val _state = mutableStateOf(CreateEditState())
  val state: MutableState<CreateEditState> get() = _state


  fun createConsumable(consumable: ConsumableFormValues) {

    _consumableRepository.createConsumable(consumable).onEach { result ->

      when (result) {
        is Resource.Success -> {

          _state.value = CreateEditState(success = true)

        }

        is Resource.Loading -> {
          _state.value = CreateEditState(isLoading = true)
        }

        is Resource.Error -> {
          val message = result.message ?: "An unexpected error occurred"

          _state.value = CreateEditState(error = message)
        }
      }

    }.launchIn(viewModelScope)

  }
}
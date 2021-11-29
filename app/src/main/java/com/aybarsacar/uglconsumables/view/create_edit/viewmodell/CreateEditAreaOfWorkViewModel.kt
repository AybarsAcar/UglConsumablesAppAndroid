package com.aybarsacar.uglconsumables.view.create_edit.viewmodell

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aybarsacar.uglconsumables.data.remote.dto.AreaOfWorkFormValues
import com.aybarsacar.uglconsumables.domain.repository.AreaOfWorkRepository
import com.aybarsacar.uglconsumables.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CreateEditAreaOfWorkViewModel @Inject constructor(
  private val _areaOfWorkRepository: AreaOfWorkRepository
) : ViewModel() {


  private val _state = mutableStateOf(CreateEditState())
  val state: MutableState<CreateEditState> get() = _state


  fun createAreaOfWork(areaOfWorkFormValues: AreaOfWorkFormValues) {

    _areaOfWorkRepository.createAreaOfWork(areaOfWorkFormValues).onEach { result ->

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
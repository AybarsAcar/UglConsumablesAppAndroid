package com.aybarsacar.uglconsumables.domain.use_case

import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableDto
import com.aybarsacar.uglconsumables.domain.repository.ConsumableRepository
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetConsumablesUseCase @Inject constructor(private val _repository: ConsumableRepository) {

  operator fun invoke(): Flow<Resource<List<ConsumableDto>>> {
    return _repository.getConsumables()
  }
}
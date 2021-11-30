package com.aybarsacar.uglconsumables.domain.repository

import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableDto
import com.aybarsacar.uglconsumables.data.remote.dto.ConsumableFormValues
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow


interface ConsumableRepository {

  fun getConsumables(serviceOrderId: Int? = null): Flow<Resource<List<ConsumableDto>>>

  fun createConsumable(consumable: ConsumableFormValues): Flow<Resource<Unit>>
}
package com.aybarsacar.uglconsumables.domain.repository

import com.aybarsacar.uglconsumables.data.remote.dto.AreaOfWorkDto
import com.aybarsacar.uglconsumables.data.remote.dto.AreaOfWorkFormValues
import com.aybarsacar.uglconsumables.util.Resource
import kotlinx.coroutines.flow.Flow


interface AreaOfWorkRepository {

  fun getAreaOfWorks(): Flow<Resource<List<AreaOfWorkDto>>>

  suspend fun createAreaOfWork(areaOfWork: AreaOfWorkFormValues)
}
package com.study.coroutines_crud.domain.usecase

import com.study.coroutines_crud.infra.database.adapter.CoroutinesStackRepository
import com.study.coroutines_crud.application.dto.StackDTO
import org.springframework.stereotype.Component

@Component
class FindStacks(
    private val stackRepository: CoroutinesStackRepository,
) {
    suspend fun execute(userId: String): List<StackDTO> = stackRepository.findStacks(userId).map { it.toDTO() }
}

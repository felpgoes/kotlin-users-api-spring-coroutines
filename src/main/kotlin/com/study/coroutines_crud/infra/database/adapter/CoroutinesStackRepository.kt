package com.study.coroutines_crud.infra.database.adapter

import com.study.coroutines_crud.domain.entity.Stack
import com.study.coroutines_crud.infra.database.repository.StackRepository
import kotlinx.coroutines.reactor.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import reactor.core.scheduler.Scheduler

@Component
class CoroutinesStackRepository(
    private val repository: StackRepository,
    private val scheduler: Scheduler,
) {
    suspend fun findStacks(userId: String): List<Stack> =
        withContext(scheduler.asCoroutineDispatcher()) { repository.findAllByUserId(userId) }
}

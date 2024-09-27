package com.study.coroutines_crud.infra.database.adapter

import com.study.coroutines_crud.domain.entity.User
import com.study.coroutines_crud.infra.database.repository.UserRepository
import kotlinx.coroutines.reactor.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import reactor.core.scheduler.Scheduler
import java.util.Optional
import kotlinx.coroutines.runBlocking

@Component
class CoroutinesUserRepository(
    private val repository: UserRepository,
    private val scheduler: Scheduler,
) {
    suspend fun findById(id: String): Optional<User> = withContext(scheduler.asCoroutineDispatcher()) { repository.findById(id) }

    suspend fun findAll(): List<User> = withContext(scheduler.asCoroutineDispatcher()) { repository.findAll() }

    suspend fun save(user: User): User = runBlocking { repository.save(user) }

    suspend fun delete(user: User) = withContext(scheduler.asCoroutineDispatcher()) { repository.delete(user) }
}

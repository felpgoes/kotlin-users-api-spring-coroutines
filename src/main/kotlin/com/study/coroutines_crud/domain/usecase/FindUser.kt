package com.study.coroutines_crud.domain.usecase

import com.study.coroutines_crud.infra.database.adapter.CoroutinesUserRepository
import org.springframework.stereotype.Component

@Component
class FindUser(
    private val userRepository: CoroutinesUserRepository,
) {
    suspend fun execute(id: String) =
        userRepository
            .findById(id)
            .orElseThrow { throw Exception("User not found") }
            .toDTO()
}

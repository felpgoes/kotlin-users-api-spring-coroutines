package com.study.coroutines_crud.domain.usecase

import com.study.coroutines_crud.infra.database.adapter.CoroutinesUserRepository
import com.study.coroutines_crud.application.dto.UserDTO
import org.springframework.stereotype.Component

@Component
class FindAllUsers(
    private val userRepository: CoroutinesUserRepository,
) {
    suspend fun execute(): List<UserDTO> = userRepository.findAll().map { it.toDTO() }
}

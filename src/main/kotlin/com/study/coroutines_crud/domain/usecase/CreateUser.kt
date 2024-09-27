package com.study.coroutines_crud.domain.usecase

import com.study.coroutines_crud.infra.database.adapter.CoroutinesUserRepository
import com.study.coroutines_crud.application.dto.UserDTO
import com.study.coroutines_crud.domain.entity.Stack
import com.study.coroutines_crud.domain.entity.User
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class CreateUser(
    private val userRepository: CoroutinesUserRepository,
) {
    suspend fun execute(body: UserDTO): UserDTO {
        val user =
            User(
                name = "${body.name}_${UUID.randomUUID()}",
                nick = body.nick,
                birthDate = body.birthDate,
            )

        body.stack?.forEach { user.stack.add(Stack(null, it.name, it.level, user)) }

        return userRepository.save(user).toDTO()
    }
}

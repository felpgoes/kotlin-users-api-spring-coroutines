package com.study.coroutines_crud.domain.usecase

import com.study.coroutines_crud.infra.database.adapter.CoroutinesUserRepository
import com.study.coroutines_crud.application.dto.UserDTO
import com.study.coroutines_crud.domain.entity.Stack
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class UpdateUser(
    private val userRepository: CoroutinesUserRepository,
) {
    suspend fun execute(
        id: String,
        body: UserDTO,
    ): UserDTO {
        val user = userRepository.findById(id).orElseThrow { throw Exception("User not found") }

        val userToUpdate =
            user.copy(
                name = body.name,
                nick = body.nick,
                birthDate = body.birthDate,
            )

        val newStacks = mutableSetOf<Stack>()
        body.stack?.forEach {
            val alreadyExists = userToUpdate.stack.find { it2 -> it2.name == it.name }
            newStacks.add(Stack(alreadyExists?.id, it.name, it.level, userToUpdate))
        }
        userToUpdate.stack.clear()
        userToUpdate.stack.addAll(newStacks)

        return userRepository.save(userToUpdate).toDTO()
    }
}

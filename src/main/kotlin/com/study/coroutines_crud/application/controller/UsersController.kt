package com.study.coroutines_crud.application.controller

import com.study.coroutines_crud.application.dto.StackDTO
import com.study.coroutines_crud.application.dto.UserDTO
import com.study.coroutines_crud.domain.usecase.CreateUser
import com.study.coroutines_crud.domain.usecase.DeleteUser
import com.study.coroutines_crud.domain.usecase.FindAllUsers
import com.study.coroutines_crud.domain.usecase.FindStacks
import com.study.coroutines_crud.domain.usecase.FindUser
import com.study.coroutines_crud.domain.usecase.UpdateUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UsersController(
    private val findAllUsers: FindAllUsers,
    private val createUser: CreateUser,
    private val updateUser: UpdateUser,
    private val findUser: FindUser,
    private val deleteUser: DeleteUser,
    private val findStacks: FindStacks,
) {
    @GetMapping
    suspend fun findAll(): ResponseEntity<List<UserDTO>> {
        // TODO Adicionar paginação
        val users = findAllUsers.execute()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}/stack")
    suspend fun findStacks(
        @PathVariable("id", required = true)
        id: String,
    ): ResponseEntity<List<StackDTO>> = ResponseEntity.ok(findStacks.execute(id))

    @PostMapping
    suspend fun store(
        @Valid
        @RequestBody
        body: UserDTO,
    ): ResponseEntity<UserDTO> = ResponseEntity.status(201).body(createUser.execute(body))

    @GetMapping("/{id}")
    suspend fun find(
        @PathVariable("id", required = true)
        id: String,
    ): ResponseEntity<UserDTO> = ResponseEntity.ok(findUser.execute(id))

    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable("id", required = true)
        id: String,
        @Valid
        @RequestBody
        body: UserDTO,
    ): ResponseEntity<UserDTO> = ResponseEntity.ok(updateUser.execute(id, body))

    @DeleteMapping("/{id}")
    suspend fun delete(
        @PathVariable("id", required = true)
        id: String,
    ): ResponseEntity<Unit> {
        deleteUser.execute(id)

        return ResponseEntity.noContent().build()
    }
}

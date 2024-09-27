package com.study.coroutines_crud.infra.database.repository

import com.study.coroutines_crud.domain.entity.Stack
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StackRepository : JpaRepository<Stack, String> {
    fun findAllByUserId(userId: String): List<Stack>
}

package com.study.coroutines_crud.infra.database.repository

import com.study.coroutines_crud.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>

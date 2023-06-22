package com.example.auth.domain.aggregate.user

import com.example.auth.domain.aggregate.user.MyUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<MyUser?, Long?> {
    fun findByEmail(username: String?): MyUser?
    fun findByUsername(username: String?): MyUser?
}
package com.example.testfordatabase.domain.aggregate.user

import com.example.testfordatabase.domain.aggregate.user.MyUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<MyUser?, Long?> {
    fun findByEmail(username: String?): MyUser?
    fun findByUsername(username: String?): MyUser?
}
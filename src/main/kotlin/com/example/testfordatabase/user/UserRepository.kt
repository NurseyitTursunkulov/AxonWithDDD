package com.example.testfordatabase.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<MyUser?, Long?> {
    fun findByEmail(username: String?): MyUser?
    fun findByUsername(username: String?): MyUser?
}
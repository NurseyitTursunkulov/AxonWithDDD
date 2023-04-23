package com.example.testfordatabase.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<MyUser?, Long?> {
    fun findByEmail(username: String?): Optional<MyUser?>?
    fun findByUsername(username: String?): Optional<MyUser?>?
}
package com.example.testfordatabase.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

//import com.google.common.base.MoreObjects;
@Entity
data class MyUser(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val email: String = "",
    val username: String = "",
    val passwordHash: String = "",
    val bio: String? = null,
    val image: String? = null,
) {


}
package com.example.testfordatabase.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.*


@Entity
@Table(name = "Customer")
data class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val email: String
)
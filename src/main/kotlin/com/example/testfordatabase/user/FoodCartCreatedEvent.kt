package com.example.testfordatabase.user

import java.util.UUID

data class FoodCartCreatedEvent(
    val foodCartId: UUID
)
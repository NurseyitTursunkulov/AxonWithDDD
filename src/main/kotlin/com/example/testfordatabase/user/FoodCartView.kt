package com.example.testfordatabase.user

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
@Entity
data class FoodCartView(
        @Id
        val foodCartId: UUID,
        val products: String
) {

}
interface FoodCartViewRepository : JpaRepository<FoodCartView, UUID>

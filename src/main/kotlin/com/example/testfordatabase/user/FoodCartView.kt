package com.example.testfordatabase.user


import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import javax.persistence.*

@Entity
data class FoodCartView(
        @Id
        val foodCartId: UUID,
        val products: String
) {

}
interface FoodCartViewRepository : JpaRepository<FoodCartView, UUID>

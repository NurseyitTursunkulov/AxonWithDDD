package com.example.testfordatabase.user

import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
internal class FoodCartProjector(private val foodCartViewRepository: FoodCartViewRepository) {

    @QueryHandler
    fun handle(query: FindFoodCartQuery): FoodCartView {
        print("hello ${query.foodCartId}")
        return foodCartViewRepository.findById(query.foodCartId).orElse(null)
    }

    @QueryHandler(queryName = "FindFoodCarts")
    fun handle(): Iterable<FoodCartView> {
        print("hello FindFoodCarts ")
        return foodCartViewRepository.findAll()
    }

    @EventHandler
    fun on(event: FoodCartCreatedEvent) {
        val foodCartView = FoodCartView(event.foodCartId, "mutableMapOf()")
        foodCartViewRepository.save(foodCartView)
    }
}

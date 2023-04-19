package com.example.testfordatabase.user

import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
internal class FoodCartProjector() {

    @QueryHandler
    fun handle(query: FindFoodCartQuery): FoodCartView {
        print("hello ${query.foodCartId}")
        return FoodCartView(query.foodCartId,"World")
    }
}

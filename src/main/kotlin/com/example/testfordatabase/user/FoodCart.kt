package com.example.testfordatabase.user

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory

@Aggregate
internal class FoodCart {

    @AggregateIdentifier
    private lateinit var foodCartId: FoodCartId
    internal lateinit var selectedProducts: MutableMap<ProductId, Int>
    private var confirmed: Boolean = false

    @CommandHandler
    constructor(command: CreateFoodCartCommand) {
        AggregateLifecycle.apply(FoodCartCreatedEvent(command.foodCartId))
    }

    @EventSourcingHandler
    fun on(event: FoodCartCreatedEvent) {
        foodCartId = event.foodCartId
        selectedProducts = mutableMapOf()
        confirmed = false
        logger.debug("hallo FoodCart FoodCartCreatedEvent Nurs hadi")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FoodCart::class.java)
    }
}

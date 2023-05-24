package com.example.testfordatabase.domain.aggregate.user

import com.example.testfordatabase.application.service.UserController
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.context.annotation.Profile


@Aggregate()
class UserAggregate {
    @AggregateIdentifier
    private var userId: String? = null
    private val logger: Logger = LoggerFactory.getLogger(UserAggregate::class.java)
    @CommandHandler
    constructor(createUserCommand: CreateUserCommand)  {
        userId = createUserCommand.userId
        logger.debug("createUserCommand CommandHandler")
        AggregateLifecycle.apply(UserCreatedEvent(createUserCommand.userId,createUserCommand.req))
    }
    @EventSourcingHandler
    fun handle(userCreatedEvent:UserCreatedEvent){
        logger.debug("userCreatedEvent UserCreatedEvent")
        userId = userCreatedEvent.userId
    }
}
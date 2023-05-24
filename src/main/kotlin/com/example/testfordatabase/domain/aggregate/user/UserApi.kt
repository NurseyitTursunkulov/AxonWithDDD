package com.example.testfordatabase.domain.aggregate.user

import com.example.testfordatabase.swagger.api.NewUserRequestData
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateUserCommand(@TargetAggregateIdentifier val userId: String,val req: NewUserRequestData)
data class UserCreatedEvent( val userId: String,val req: NewUserRequestData)
package com.example.testfordatabase.application.command

import com.example.testfordatabase.swagger.api.NewUserRequestData
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateUserCommand(@TargetAggregateIdentifier val userId: String, val newUserRequestData: NewUserRequestData) {
}
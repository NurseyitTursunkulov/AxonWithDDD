package com.example.testfordatabase.domain.aggregate.user

import com.example.testfordatabase.application.dto.mapper.toUserResponse
import com.example.testfordatabase.application.exception.EmailAlreadyUsedException
import com.example.testfordatabase.application.exception.UsernameAlreadyUsedException
import com.example.testfordatabase.application.service.ok
import com.example.testfordatabase.domain.service.AuthenticationService
import com.example.testfordatabase.security.JwtService
import com.example.testfordatabase.swagger.api.NewUserData
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryUpdateEmitter
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
class UserProjection (
    var userRepository: UserRepository,
    var jwtService: JwtService,
    var authenticationService: AuthenticationService,
     val updateEmitter: QueryUpdateEmitter
){
    @EventHandler
    fun handle(userCreatedEvent:UserCreatedEvent){
        print("EventHandler UserProjection")
        val newUserData: NewUserData? = userCreatedEvent.req.user
        val username: String? = newUserData?.username
        val email: String? = newUserData?.email
        userRepository
            .findByUsername(username)
            ?.let { u -> throw UsernameAlreadyUsedException("Username already used - $username") }
        userRepository
            .findByEmail(email)
            ?.let { u -> throw EmailAlreadyUsedException("Email already used - $email") }
        email?.let {
            username?.let {
                newUserData.password?.let {
                    authenticationService.encodePassword(newUserData.password)?.let { passwort ->
                        val newUser = MyUser(email = email, username = username, passwordHash = passwort)
                        val user: MyUser = userRepository.save(newUser)
//                        return ok(toUserResponse(user, jwtService.generateToken(user)))
                    }
                }
            }
        }
    }
}
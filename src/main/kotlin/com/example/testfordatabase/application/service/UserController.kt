package com.example.testfordatabase.application.service

import com.example.testfordatabase.domain.service.AuthenticationService
import com.example.testfordatabase.application.exception.UserNotFoundException
import com.example.testfordatabase.application.exception.EmailAlreadyUsedException
import com.example.testfordatabase.application.exception.InvalidPasswordException
import com.example.testfordatabase.application.exception.UsernameAlreadyUsedException
import com.example.testfordatabase.application.dto.mapper.toUserResponse
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.aggregate.user.UserRepository
import com.example.testfordatabase.security.JwtService
import com.example.testfordatabase.swagger.api.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.NativeWebRequest
import java.util.*

@Service
@Transactional
@RestController
class UserController(
    var userRepository: UserRepository,
    var jwtService: JwtService,
    var authenticationService: AuthenticationService
) {

     val request: Optional<NativeWebRequest>
        get() = TODO("Not yet implemented")

    @GetMapping("getcurrentuser")
    fun getCurrentUser(): ResponseEntity<UserResponseData?>? {
        return authenticationService
            .currentMyUser
            ?.let { u -> ok(toUserResponse(u, authenticationService.currentToken?:"")) }
            ?:run{ throw UserNotFoundException("User not found") }
    }

    @PostMapping("/createuser")
     fun createUser( @RequestBody req: NewUserRequestData): ResponseEntity<UserResponseData?>? {
        val newUserData: NewUserData? = req.user
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
                     authenticationService.encodePassword(newUserData.password)?.let {passwort->
                         val newUser = MyUser(email = email, username =  username, passwordHash = passwort)
                         val user: MyUser = userRepository.save(newUser)
                         return ok(toUserResponse(user, jwtService.generateToken(user)))
                     }

                 }
             }
         }
        return null
    }
    @PostMapping("/login")
    fun login(@RequestBody body: LoginUserRequestData): ResponseEntity<UserResponseData?>? {
        val loginUserData: LoginUserData? = body.user
        val email: String? = loginUserData?.email
        return authenticationService
            .authenticate(loginUserData?.email, loginUserData?.password)
            ?.let { u -> ok(toUserResponse(u, jwtService.generateToken(u))) }
            ?:run { throw InvalidPasswordException("Can not authenticate - $email") }
    }

}
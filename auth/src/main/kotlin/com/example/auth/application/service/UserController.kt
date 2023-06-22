package com.example.auth.application.service

import com.example.articles.security.JwtService
import com.example.auth.application.dto.mapper.toUserResponse
import com.example.auth.application.dto.mapper.updateUser
import com.example.auth.application.exception.EmailAlreadyUsedException
import com.example.auth.application.exception.InvalidPasswordException
import com.example.auth.application.exception.UserNotFoundException
import com.example.auth.application.exception.UsernameAlreadyUsedException
import com.example.auth.domain.aggregate.user.MyUser
import com.example.auth.domain.aggregate.user.UserRepository
import com.example.auth.domain.service.AuthenticationService
import com.example.testfordatabase.swagger.api.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.NativeWebRequest
import java.util.*
import java.util.concurrent.CompletableFuture
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Service
@Transactional
@RestController
class UserController(
    var userRepository: UserRepository,
    var authenticationService: AuthenticationService,
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    val request: Optional<NativeWebRequest>
        get() = TODO("Not yet implemented")

    @GetMapping("getcurrentuser")
    fun getCurrentUser(): ResponseEntity<UserResponseData?>? {
        return authenticationService
            .currentMyUser
            ?.let { u -> ok(toUserResponse(u, authenticationService.currentToken ?: "")) }
            ?: run { throw UserNotFoundException("User not found") }
    }
    private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)
//    @PostMapping("/createuser2")
//    fun createUser2(@RequestBody req: NewUserRequestData): CompletableFuture<ResponseEntity<UserResponseData>?> {
//            logger.debug("This is a debug level message")
//        return commandGateway.send<UserResponseData?>(CreateUserCommand(UUID.randomUUID().toString(),req)).thenApply {
//            logger.debug("This is a debug level message")
//            ok(it)
//        }
//            .exceptionally {
////                val responseData = UserResponseData("Error occurred: ${ex.message}")
//                    (ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null))
////                return ResponseEntity.badRequest()!!
//            }
//    }


    @PostMapping("/createuser")
    fun createUser(@RequestBody req: NewUserRequestData): ResponseEntity<UserResponseData?>? {
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
                    authenticationService.encodePassword(newUserData.password)?.let { passwort ->
                        val newUser = MyUser(email = email, username = username, passwordHash = passwort)
                        val user: MyUser = userRepository.save(newUser)
                        return ok(toUserResponse(user,"jwtService.generateToken(u)"))
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
            ?.let { u -> ok(toUserResponse(u, "jwtService.generateToken(u)")) }
            ?: run { throw InvalidPasswordException("Can not authenticate - $email") }
    }

    @PostMapping("/updateCurrentUser")
    fun updateCurrentUser(@RequestBody req: UpdateUserRequestData): ResponseEntity<UserResponseData?>? {
        val user: MyUser = authenticationService
            .currentMyUser ?: kotlin.run {
            throw UserNotFoundException("User not found")
        }
        val update: UpdateUserData? = req.user
        val email: String? = update?.email
        if (email != null && email != user.email) {
            userRepository
                .findByEmail(email)?.let {
                    throw EmailAlreadyUsedException("Email already used - $email")
                }
        }
        val username: String? = update?.username
        if (username != null && username != user.username) {
            userRepository
                .findByUsername(username)?.let {
                    throw UsernameAlreadyUsedException("Username already used - $username")
                }
        }
        update?.let { updateUser(user, it) }
        return ok(toUserResponse(user, authenticationService.currentToken ?: ""))
    }

}

fun <T> ok(body: T): ResponseEntity<T>? {
    return ResponseEntity(body, HttpStatus.OK)
}
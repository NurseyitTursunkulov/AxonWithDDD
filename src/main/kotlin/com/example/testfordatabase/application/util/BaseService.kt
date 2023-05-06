package com.example.testfordatabase.application.util

import com.example.testfordatabase.application.exception.UserNotFoundException
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

//import io.realworld.backend.application.exception.UserNotFoundException;
//import io.realworld.backend.domain.aggregate.user.User;
//import io.realworld.backend.domain.service.AuthenticationService;
abstract class BaseService {
    fun <T> ok(body: T): ResponseEntity<T> {
        return ResponseEntity(body, HttpStatus.OK)
    }

    /** Returns AuthenticationService.  */
    abstract val authenticationService: AuthenticationService

    /**
     * Returns current user or throws an exceptions.
     *
     * @return current user
     * @throws UserNotFoundException if the current is anonymous
     */
    fun currentUserOrThrow(): MyUser {
        return authenticationService
            .currentMyUser?: kotlin.run { throw UserNotFoundException("Can not authenticate") }
    }
}
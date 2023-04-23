package com.example.testfordatabase

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
//    fun currentUserOrThrow(): User {
//        return authenticationService
//            .currentUser
//            .orElseThrow { UserNotFoundException("Can not authenticate") }
//    }
}
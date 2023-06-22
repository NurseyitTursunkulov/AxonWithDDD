package com.example.auth.domain.service

import com.example.auth.domain.aggregate.user.MyUser

interface AuthenticationService {
    /** Returns current authenticated user.  */
    val currentMyUser: MyUser?

    /** Returns a JWT token used to authenticate current user.  */
    val currentToken: String?

    /** Checks if provided credentials correct and returns relevant user.  */
    fun authenticate(email: String?, password: String?): MyUser?

    /** Returns a hash of the password.  */
    fun encodePassword(password: String?): String?
}
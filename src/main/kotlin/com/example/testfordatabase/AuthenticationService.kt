package com.example.testfordatabase

import com.example.testfordatabase.user.MyUser
import java.util.*

interface AuthenticationService {
    /** Returns current authenticated user.  */
    val currentMyUser: Optional<MyUser?>?

    /** Returns a JWT token used to authenticate current user.  */
    val currentToken: Optional<String>?

    /** Checks if provided credentials correct and returns relevant user.  */
    fun authenticate(email: String?, password: String?): Optional<MyUser?>?

    /** Returns a hash of the password.  */
    fun encodePassword(password: String?): String?
}
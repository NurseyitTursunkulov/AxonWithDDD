package com.example.articles.security

import com.example.auth.domain.aggregate.user.MyUser
import java.util.*

interface JwtService {
    /** Generates JWT token for a given user.  */
    fun generateToken(myUser: MyUser?): String?

    /** Finds a user that given token was generated for.  */
    fun getUser(token: String?): Optional<MyUser?>?
}
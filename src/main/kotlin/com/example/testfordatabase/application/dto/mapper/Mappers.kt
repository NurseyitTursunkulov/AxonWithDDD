package com.example.testfordatabase.application.dto.mapper

import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.swagger.api.UpdateUserData
import com.example.testfordatabase.swagger.api.UserData
import com.example.testfordatabase.swagger.api.UserResponseData


fun toUserResponse(u: MyUser, token: String?): UserResponseData? {
        val userData = UserData()
        userData.username = u.username
        userData.email = u.email
        u.bio?.let { userData.bio = it }
        u.image?.let { userData.image = it }
        if (token != null) {
            userData.token = token
        }
        val res = UserResponseData()
        res.user = (userData)
        return res
    }

fun updateUser(user: MyUser, update: UpdateUserData) {
    val email: String? = update.email
    if (email != null) {
        user.email = email
    }
    val username: String? = update.username
    if (username != null) {
        user.username = username
    }
    val bio: String? = update.bio
    if (bio != null) {
        user.bio = bio
    }
    val image: String? = update.image
    if (image != null) {
        user.image = image
    }
}

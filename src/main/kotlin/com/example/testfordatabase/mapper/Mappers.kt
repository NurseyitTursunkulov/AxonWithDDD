package com.example.testfordatabase.mapper

import com.example.testfordatabase.swagger.api.UserData
import com.example.testfordatabase.swagger.api.UserResponseData
import com.example.testfordatabase.user.MyUser


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

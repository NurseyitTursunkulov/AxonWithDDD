package com.example.auth.application.dto.mapper

import com.example.auth.domain.aggregate.user.MyUser
import com.example.testfordatabase.swagger.api.*
import com.google.common.collect.ImmutableSet
import java.time.ZoneOffset
import java.util.stream.Collectors



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

fun toProfile(user: MyUser, isFollowing: Boolean): ProfileData {
    val profile = ProfileData()
    profile.username = (user.username)
    user.bio?.let { profile.bio = it }
    profile.following = (isFollowing)
    return profile
}

class FavouriteInfo(val isFavorited: Boolean, val favoritesCount: Int)
class MultipleFavouriteInfo(
    val favouritedArticleIds: Set<Long>, val favouritedCountByArticleId: Map<Long, Long>
)
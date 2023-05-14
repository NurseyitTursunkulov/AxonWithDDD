package com.example.testfordatabase.application.dto.mapper

import com.example.testfordatabase.domain.aggregate.article.Article
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.swagger.api.*
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import org.mapstruct.factory.Mappers
import java.time.ZoneOffset


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

fun fromNewArticleData(newArticleData: NewArticleData, user: MyUser): Article {
    val article = Article()
    newArticleData.title?.let { article.setTitle(it) }
    article.description = (newArticleData.description)
    article.body = (newArticleData.body)
    article.author = (user)
    newArticleData.tagList?.toMutableList()?.let {

    article.tags = (ImmutableSet.copyOf(it))
    }
    return article
}

fun toSingleArticleResponse(
    article: Article, favouriteInfo: FavouriteInfo, isFollowingAuthor: Boolean
): SingleArticleResponseData {
    val resp = SingleArticleResponseData()
    val articleData: ArticleData = toArticleData(article, favouriteInfo, isFollowingAuthor)
    resp.article = (articleData)
    return resp
}

private fun toArticleData(
    article: Article, favouriteInfo: FavouriteInfo, isFollowingAuthor: Boolean
): ArticleData {
    val articleData = ArticleData().apply {
        slug = article.slug
        title = article.title
        description = article.description
        body = article. body
        tagList = article.tags?.toMutableList()
        createdAt = article.createdAt?.atOffset(ZoneOffset.UTC)
        updatedAt = article.updatedAt?.atOffset(ZoneOffset.UTC)
        favorited = favouriteInfo.isFavorited
        favoritesCount = favouriteInfo.favoritesCount
        author = toProfile(article.author,isFollowingAuthor)
    }
    return articleData
}

fun toProfile(user: MyUser, isFollowing: Boolean): ProfileData {
    val profile = ProfileData()
    profile.username = (user.getUsername())
    user.bio?.let { profile.bio = it }
    profile.following = (isFollowing)
    return profile
}
class FavouriteInfo(val isFavorited: Boolean, val favoritesCount: Int)
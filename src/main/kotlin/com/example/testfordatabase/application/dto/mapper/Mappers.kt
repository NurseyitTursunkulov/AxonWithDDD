package com.example.testfordatabase.application.dto.mapper

import com.example.testfordatabase.domain.aggregate.article.Article
import com.example.testfordatabase.domain.aggregate.comment.Comment
import com.example.testfordatabase.domain.aggregate.user.MyUser
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

fun updateArticle(article: Article, updateArticleData: UpdateArticleData) {
    val title: String = updateArticleData.title
    if (title != null) {
        article.setTitle(title)
    }
    val description: String = updateArticleData.description
    if (description != null) {
        article.description = (description)
    }
    val body: String = updateArticleData.body
    if (body != null) {
        article.body = (body)
    }
}

fun fromNewCommentData(
    commentData: NewCommentData, article: Article, author: MyUser
): Comment {
    val comment = Comment().apply {
        this.article = article
        this.author = author
        this.body = commentData.body
    }
    return comment
}

fun toSingleCommentResponseData(
    comment: Comment, isFollowingAuthor: Boolean
): SingleCommentResponseData {
    val resp = SingleCommentResponseData()
    val commentData = CommentData()
    commentData.id = comment.id.toInt()
    commentData.author = toProfile(comment.author, isFollowingAuthor)
    commentData.body = comment.body
    commentData.createdAt = comment.createdAt?.atOffset(ZoneOffset.UTC)
    commentData.updatedAt = comment.updatedAt?.atOffset(ZoneOffset.UTC)
    resp.comment = commentData
    return resp
}

fun toMultipleCommentsResponseData(
    comments: List<Comment>?, followingIds: Set<Long?>
): MultipleCommentsResponseData {
    val commentsResponseData = MultipleCommentsResponseData()
    val commentDataList = comments?.stream()
        ?.map<CommentData> { c: Comment ->

            val commentData = CommentData()
            commentData.id = (c.id.toInt())
            commentData.body = (c.body)
            commentData.author = (
                toProfile(c.author, followingIds.contains(c.author.id))
            )
            commentData.createdAt = (c.createdAt?.atOffset(ZoneOffset.UTC))
            commentData.updatedAt = (c.updatedAt?.atOffset(ZoneOffset.UTC))
            commentData
        }
        ?.collect(Collectors.toList())
    commentsResponseData.comments = commentDataList
    return commentsResponseData
}
class FavouriteInfo(val isFavorited: Boolean, val favoritesCount: Int)
class MultipleFavouriteInfo(
    val favouritedArticleIds: Set<Long>, val favouritedCountByArticleId: Map<Long, Long>
)

fun toMultipleArticlesResponseData(
    articles: Collection<Article>,
    multipleFavouriteInfo: MultipleFavouriteInfo,
    followingIds: Set<Long?>,
    count: Int
): MultipleArticlesResponseData {
    val multipleArticlesResponseData = MultipleArticlesResponseData()
    val articleDataList = articles.map { article: Article ->
                toArticleData(
                    article,
                    FavouriteInfo(
                        multipleFavouriteInfo
                            .favouritedArticleIds
                            .contains(article.id),
                        multipleFavouriteInfo.favouritedCountByArticleId
                            .getOrDefault(article.id, 0L)
                            .toInt()
                    ),
                    followingIds.contains(article.author.id)
                )
            }
    multipleArticlesResponseData.setArticles(articleDataList)
    multipleArticlesResponseData.articlesCount = count
    return multipleArticlesResponseData
}

fun toTagsResponseData(tags: List<String>?): TagsResponseData? {
    val tagsResponseData = TagsResponseData()
    tagsResponseData.tags(tags)
    return tagsResponseData
}
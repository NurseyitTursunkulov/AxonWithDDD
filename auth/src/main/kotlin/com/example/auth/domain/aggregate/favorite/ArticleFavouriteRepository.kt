package com.example.auth.domain.aggregate.favorite

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ArticleFavouriteRepository : CrudRepository<ArticleFavourite?, ArticleFavouriteId?> {
    class FavouriteCount(val articleId: Long, val count: Long)

    fun countByIdArticleId(articleId: Long): Int

    @Query(
        "SELECT new com.example.auth.domain.aggregate.favorite.ArticleFavouriteRepository$"
                + "FavouriteCount(f.id.articleId, COUNT(*)) "
                + "FROM ArticleFavourite f WHERE f.id.articleId IN (:articleIds) GROUP BY f.id.articleId"
    )
    fun countByIdArticleIds(articleIds: List<Long?>?): List<FavouriteCount>?
    fun findByIdUserId(userId: Long): List<ArticleFavourite>?
}
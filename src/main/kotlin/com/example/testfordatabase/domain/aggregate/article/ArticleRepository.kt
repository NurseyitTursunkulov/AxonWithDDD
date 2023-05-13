package com.example.testfordatabase.domain.aggregate.article

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface ArticleRepository : PagingAndSortingRepository<Article?, Long?> , CrudRepository<Article, Long>{
    fun findBySlug(slug: String?): Optional<Article?>?
    fun findByAuthorIdIn(authorIds: Collection<Long?>?, pageable: Pageable?): List<Article?>?

//    @Query("""
//    SELECT DISTINCT a
//    FROM Article a
//    LEFT JOIN a.tags t
//    LEFT JOIN a.author p
//    LEFT JOIN ArticleFavourite f ON a.id = f.id.articleId
//    LEFT JOIN MyUser fu ON fu.id = f.id.userId
//    WHERE (:tag IS NULL OR :tag MEMBER OF a.tags)
//    AND (:author IS NULL OR p.username = :author)
//    AND (:favorited IS NULL OR fu.username = :favorited)
//""")
//    fun findByFilters(
//        @Param("tag") tag: String?,
//        @Param("author") author: String?,
//        @Param("favorited") favorited: String?,
//        pageable: Pageable
//    ): List<Article>
//
//
//    @Query(
//        "SELECT COUNT(DISTINCT a.id) FROM Article a "
//                + "LEFT JOIN a.tags t "
//                + "LEFT JOIN a.author p "
//                + "LEFT JOIN ArticleFavourite f ON a.id = f.id.articleId "
//                + "LEFT JOIN MyUser fu ON fu.id = f.id.userId "
//                + "WHERE "
//                + "(:tag IS NULL OR :tag IN t) AND "
//                + "(:author IS NULL OR p.username = :author) AND "
//                + "(:favorited IS NULL OR fu.username = :favorited)"
//    )
//    fun countByFilter(tag: String?, author: String?, favorited: String?): Int
    fun countByAuthorIdIn(authorIds: Collection<Long?>?): Int

    @Query("SELECT t from Article a LEFT JOIN a.tags t")
    fun findAllTags(): List<String?>?
}
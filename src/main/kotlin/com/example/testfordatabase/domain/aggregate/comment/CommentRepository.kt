package com.example.testfordatabase.domain.aggregate.comment

import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment?, Long?> {
    fun findByArticleId(articleId: Long): List<Comment>?
    fun deleteByArticleId(articleId: Long)
}
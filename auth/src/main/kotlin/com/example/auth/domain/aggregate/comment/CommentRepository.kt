package com.example.auth.domain.aggregate.comment

import com.example.auth.domain.aggregate.comment.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment?, Long?> {
    fun findByArticleId(articleId: Long): List<Comment>?
    fun deleteByArticleId(articleId: Long)
}
package com.example.testfordatabase.domain.aggregate.favorite

import com.google.common.base.MoreObjects
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
class ArticleFavouriteId : Serializable { //todo convert to dataclass
    var userId: Long = 0
    var articleId: Long = 0

    protected constructor()
    constructor(userId: Long, articleId: Long) {
        this.userId = userId
        this.articleId = articleId
    }

    /** {@inheritDoc}  */
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as ArticleFavouriteId
        return userId == that.userId && articleId == that.articleId
    }

    override fun hashCode(): Int {
        return Objects.hash(userId, articleId)
    }

    /** {@inheritDoc}  */
    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("userId", userId)
            .add("articleId", articleId)
            .toString()
    }
}
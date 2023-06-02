package com.example.testfordatabase.domain.aggregate.favorite

import com.google.common.base.MoreObjects
import javax.persistence.*
import org.springframework.lang.NonNull

@Entity
class ArticleFavourite {
    @EmbeddedId
    @NonNull
    var id = ArticleFavouriteId(0, 0)

    constructor(userId: Long, articleId: Long) {
        id = ArticleFavouriteId(userId, articleId)
    }

    constructor()

    override fun toString(): String {
        return MoreObjects.toStringHelper(this).add("id", id).toString()
    }
}
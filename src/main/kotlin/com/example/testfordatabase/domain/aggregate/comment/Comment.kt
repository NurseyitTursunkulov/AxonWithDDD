package com.example.testfordatabase.domain.aggregate.comment

import com.example.testfordatabase.domain.aggregate.article.Article
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.google.common.base.MoreObjects
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @ManyToOne
    var article: Article = Article(),
    @ManyToOne
    var author: MyUser = MyUser(),
    var body: @NotNull String? = "",
    var createdAt: @NotNull Instant? = Instant.now(),
    var updatedAt: @NotNull Instant? = Instant.now()
) {




    /** {@inheritDoc}  */
    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("article", article)
            .add("author", author)
            .add("createdAt", createdAt)
            .add("updatedAt", updatedAt)
            .toString()
    }
}
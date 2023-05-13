package com.example.testfordatabase.domain.aggregate.article

import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.google.common.base.MoreObjects
import com.google.common.collect.ImmutableSet
import jakarta.persistence.*
import java.time.Instant
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import javax.validation.constraints.NotNull

@Entity
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var slug: @NotNull String? = "",
    var title: @NotNull String? = "",
    var description: @NotNull String? = "",
    var body: @NotNull String? = "",
    @ElementCollection(fetch = FetchType.EAGER)
     var tags: @NotNull MutableSet<String>? = ImmutableSet.of(),
    @ManyToOne
    var author: @NotNull MyUser = MyUser(),
    var createdAt: @NotNull Instant? = Instant.now(),
    var updatedAt: @NotNull Instant? = Instant.now()
) {

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }

    /** {@inheritDoc}  */
    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("slug", slug)
            .add("title", title)
            .add("description", description)
            .add("tags", tags)
            .add("author", author)
            .add("createdAt", createdAt)
            .add("updatedAt", updatedAt)
            .toString()
    }
}
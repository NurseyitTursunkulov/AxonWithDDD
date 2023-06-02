package com.example.testfordatabase.domain.aggregate.follow

import com.google.common.base.MoreObjects
import javax.persistence.*
import org.springframework.lang.NonNull

@Entity
data class FollowRelation(
    @EmbeddedId
    @NonNull
    var id: FollowRelationId = FollowRelationId(0, 0)
) {
    override fun toString(): String {
        return MoreObjects.toStringHelper(this).add("id", id).toString()
    }
}
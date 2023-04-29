package com.example.testfordatabase.follow

import com.google.common.base.MoreObjects
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import org.springframework.lang.NonNull

@Entity
data class FollowRelation(
    @EmbeddedId
    @NonNull
    var id:FollowRelationId = FollowRelationId(0, 0)
) {
    override fun toString(): String {
        return MoreObjects.toStringHelper(this).add("id", id).toString()
    }
}
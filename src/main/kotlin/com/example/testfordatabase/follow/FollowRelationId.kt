package com.example.testfordatabase.follow

import com.google.common.base.MoreObjects
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
data class FollowRelationId(var followerId: Long = 0,
                            var followeeId: Long = 0) : Serializable {
    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("followerId", followerId)
            .add("followeeId", followeeId)
            .toString()
    }
}
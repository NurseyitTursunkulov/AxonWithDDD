package com.example.testfordatabase.domain.aggregate.follow

import com.google.common.base.MoreObjects
import javax.persistence.*
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
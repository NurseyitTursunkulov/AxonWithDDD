package com.example.auth.domain.aggregate.follow

import com.example.auth.domain.aggregate.follow.FollowRelation
import com.example.auth.domain.aggregate.follow.FollowRelationId
import org.springframework.data.repository.CrudRepository

interface FollowRelationRepository : CrudRepository<FollowRelation?, FollowRelationId?> {
    fun findByIdFollowerId(followerId: Long): List<FollowRelation>?
}
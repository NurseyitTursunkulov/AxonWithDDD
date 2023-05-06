package com.example.testfordatabase.domain.aggregate.follow

import org.springframework.data.repository.CrudRepository

interface FollowRelationRepository : CrudRepository<FollowRelation?, FollowRelationId?> {
    fun findByIdFollowerId(followerId: Long): List<FollowRelation?>?
}
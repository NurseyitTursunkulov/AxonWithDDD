package com.example.testfordatabase.application.service

import com.example.testfordatabase.application.exception.UserNotFoundException
import com.example.testfordatabase.application.util.BaseService
import com.example.testfordatabase.domain.aggregate.follow.FollowRelation
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationId
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationRepository
import com.example.testfordatabase.swagger.api.ProfileData
import com.example.testfordatabase.swagger.api.ProfileResponseData
import com.example.testfordatabase.swagger.api.ProfilesApiDelegate
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.aggregate.user.UserRepository
import com.example.testfordatabase.domain.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@Service
@Transactional
@RestController
class ProfileService
/** Creates ProfileService instance.  */ @Autowired constructor(
    private val userRepository: UserRepository,
    override var authenticationService: AuthenticationService,
    val followRelationRepository: FollowRelationRepository
) : BaseService(), ProfilesApiDelegate {
    /** {@inheritDoc}  */
    @PostMapping(value = ["/profiles/{username}/follow"], produces = ["application/json"])
    override fun followUserByUsername(@PathVariable("username") username: String?): ResponseEntity<ProfileResponseData?>? {
        val currentUser: MyUser = currentUserOrThrow()
        val user: MyUser = userRepository
            .findByUsername(username)
            ?: run { throw UserNotFoundException(username) }

        val followRelationId = FollowRelationId(currentUser.id, user.id)
        followRelationRepository
            .findById(followRelationId)
            .or {
                val followRelation = FollowRelation(FollowRelationId(currentUser.id, user.id))
                followRelationRepository.save(followRelation)
                Optional.of(followRelation)
            }

        return ok(toProfileResponse(user, true))
    }

    /** {@inheritDoc}  */
    @GetMapping(value = ["/profiles/{username}"], produces = ["application/json"])
    override fun getProfileByUsername(@PathVariable("username") username: String?): ResponseEntity<ProfileResponseData?>? {
        val currentUser: MyUser? = authenticationService.currentMyUser
        val func: (MyUser) -> Boolean = { user ->
            currentUser?.let {
                followRelationRepository
                    .findById(FollowRelationId(it.id, user.id)).isPresent
            } ?: false
        }

        return userRepository.findByUsername(username)?.let {
            ok(toProfileResponse(it, func(it)))
        } ?: kotlin.run {
            throw UserNotFoundException(username)
        }
    }

    /** {@inheritDoc}  */
    @DeleteMapping(value = ["/profiles/{username}/follow"], produces = ["application/json"])
    override fun unfollowUserByUsername(@PathVariable username: String?): ResponseEntity<ProfileResponseData?>? {
        val currentUser: MyUser = currentUserOrThrow()
        val user: MyUser = userRepository
            .findByUsername(username)
            ?:run { throw UserNotFoundException(username) }
        val followRelationId = FollowRelationId(currentUser.id, user.id)
        followRelationRepository.deleteById(followRelationId)
        return ok(toProfileResponse(user, false))
    }

    companion object {
        fun toProfileResponse(myUser: MyUser?, isFollowing: Boolean): ProfileResponseData? {
            val profileResponse = ProfileResponseData()
            val profile = toProfile(myUser, isFollowing)
            profileResponse.profile = profile
            return profileResponse
        }

        fun toProfile(
            myUser: MyUser?,
            isFollowing: Boolean
        ): ProfileData {
            return ProfileData(username = myUser?.username, bio = myUser?.bio, image = myUser?.image, following = isFollowing)
        }
    }

}
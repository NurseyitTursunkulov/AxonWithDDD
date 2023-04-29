package com.example.testfordatabase

import com.example.testfordatabase.follow.FollowRelation
import com.example.testfordatabase.follow.FollowRelationId
import com.example.testfordatabase.follow.FollowRelationRepository
import com.example.testfordatabase.swagger.api.ProfileData
import com.example.testfordatabase.swagger.api.ProfileResponseData
import com.example.testfordatabase.swagger.api.ProfilesApiDelegate
import com.example.testfordatabase.user.MyUser
import com.example.testfordatabase.user.UserRepository
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
        var currentUser = currentUserOrThrow();

        var user = userRepository.findByUsername(username)

        currentUser?.let {
            user?.let {
                var followRelationId = FollowRelationId(currentUser.id, user.id);
                followRelationRepository.findById(followRelationId).or {
                    var followRelation = FollowRelation(followRelationId)
                    followRelationRepository.save(followRelation)
                    return@or Optional.of(followRelation)
                }
            }
        }

        return ok(toProfileResponse(user, true));
    }

    /** {@inheritDoc}  */
    @GetMapping(value = ["/profiles/{username}"], produces = ["application/json"])
    override fun getProfileByUsername(@PathVariable("username") username: String?): ResponseEntity<ProfileResponseData?>? {
        return userRepository
            .findByUsername(username)
            ?.let {
                ok(toProfileResponse(it, false))
            } ?: run {
            throw UserNotFoundException(username)
        }
    }

    /** {@inheritDoc}  */
    @DeleteMapping(value = ["/profiles/{username}/follow"], produces = ["application/json"])
    override fun unfollowUserByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
        val currentUser = currentUserOrThrow();
        val user = userRepository
            .findByUsername(username) ?: kotlin.run { throw UserNotFoundException(username) }
        val followRelationId = FollowRelationId(currentUser.id, user.id);
        followRelationRepository.deleteById(followRelationId);
        return ok(toProfileResponse(user, false));
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
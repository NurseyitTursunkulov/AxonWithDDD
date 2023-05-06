package com.example.testfordatabase.application.service

import com.example.testfordatabase.application.exception.UserNotFoundException
import com.example.testfordatabase.application.util.BaseService
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationId
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationRepository
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.aggregate.user.UserRepository
import com.example.testfordatabase.domain.service.AuthenticationService
import com.example.testfordatabase.swagger.api.ProfileResponseData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

fun <T> ok(body: T): ResponseEntity<T>? {
    return ResponseEntity(body, HttpStatus.OK)
}

@RestController
@Service
@Transactional
@RequestMapping()
class ProfilesController(
    private val userRepository: UserRepository,
    var followRelationRepository: FollowRelationRepository,
    override val authenticationService: AuthenticationService
) : BaseService() {
    @GetMapping("/profiles/{username}")
    fun getProfileByUsername(@PathVariable username: String?): ResponseEntity<ProfileResponseData?>? {
        print("buuuka")
        val currentUser: MyUser? = authenticationService.currentMyUser
        val func : (MyUser)->Boolean = {user->
            currentUser?.let {
                followRelationRepository
                    .findById(FollowRelationId(it.id, user.id)).isPresent
            }?:false
        }

        return userRepository.findByUsername(username)?.let {
            ok(toProfileResponse(it, func(it)))
        } ?: kotlin.run {
            throw UserNotFoundException(username)
        }
    }
@PostMapping("/profiles/{username}/follow")
    fun followUserByUsername(@PathVariable username: String?): ResponseEntity<ProfileResponseData?>? {
        val currentUser: MyUser = currentUserOrThrow()
        val user: MyUser = userRepository
            .findByUsername(username)
            ?:run { throw UserNotFoundException(username) }

//        val followRelationId = FollowRelationId(currentUser.getId(), user.getId())
//        followRelationRepository
//            .findById(followRelationId)
//            .or {
//                val followRelation = FollowRelation(currentUser.getId(), user.getId())
//                followRelationRepository.save(followRelation)
//                Optional.of(followRelation)
//            }
        return ok(toProfileResponse(user, true))
    }

    fun toProfileResponse(myUser: MyUser?, isFollowing: Boolean): ProfileResponseData? {
        val profileResponse = ProfileResponseData()
        val profile = ProfileService.toProfile(myUser, isFollowing)
        profileResponse.profile = profile
        return profileResponse
    }
}
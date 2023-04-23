package com.example.testfordatabase

import com.example.testfordatabase.swagger.api.ProfileData
import com.example.testfordatabase.swagger.api.ProfileResponseData
import com.example.testfordatabase.swagger.api.ProfilesApiDelegate
import com.example.testfordatabase.user.MyUser
import com.example.testfordatabase.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

//import io.realworld.backend.application.exception.UserNotFoundException;
//import io.realworld.backend.application.util.BaseService;
//import io.realworld.backend.domain.aggregate.follow.FollowRelation;
//import io.realworld.backend.domain.aggregate.follow.FollowRelationId;
//import io.realworld.backend.domain.aggregate.follow.FollowRelationRepository;
//import io.realworld.backend.domain.aggregate.user.User;
//import io.realworld.backend.domain.aggregate.user.UserRepository;
//import io.realworld.backend.domain.service.AuthenticationService;
//import io.realworld.backend.rest.api.ProfileResponseData;
//import io.realworld.backend.rest.api.ProfilesApiDelegate;
//import static io.realworld.backend.application.dto.Mappers.toProfileResponse;
@Service
@Transactional
class ProfileService
/** Creates ProfileService instance.  */ @Autowired constructor(
    private val userRepository: UserRepository
) : BaseService(), ProfilesApiDelegate {
    /** {@inheritDoc}  */
    override fun followUserByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
        return null
        //    final var currentUser = currentUserOrThrow();
//
//    final var user =
//        userRepository
//            .findByUsername(username)
//            .orElseThrow(() -> new UserNotFoundException(username));
//
//    final var followRelationId = new FollowRelationId(currentUser.getId(), user.getId());
//    followRelationRepository
//        .findById(followRelationId)
//        .or(
//            () -> {
//              final var followRelation = new FollowRelation(currentUser.getId(), user.getId());
//              followRelationRepository.save(followRelation);
//              return Optional.of(followRelation);
//            });
//
//    return ok(toProfileResponse(user, true));
    }

    /** {@inheritDoc}  */
    override fun getProfileByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
      return  userRepository
          .findByUsername(username)
          ?.map {
              ok(toProfileResponse(it, false))
          }?.orElseThrow{
              UserNotFoundException(username)
          }

//        return userRepository
//            .findByUsername(username)
//            .map<ResponseEntity<ProfileResponseData>>(Function { u: User? -> ok(toProfileResponse(u, false)) })
//            .orElseThrow { UserNotFoundException(username) }
    }

    /** {@inheritDoc}  */
    override fun unfollowUserByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
        return null
        //    final var currentUser = currentUserOrThrow();
//
//    final var user = new UserNotFoundException(username);
//
//    final var followRelationId = new FollowRelationId(currentUser.getId(), user.getId());
//    followRelationRepository.deleteById(followRelationId);
//
//    return ok(toProfileResponse(user, false));
    }

    /** {@inheritDoc}  */
//    override fun getAuthenticationService(): AuthenticationService? {
//        return null
//    }

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
            //    profile.setUsername(user.username);
//    user.getBio().ifPresent(profile::setBio);
//    user.getImage().ifPresent(profile::setImage);
//    profile.setFollowing(isFollowing);
            return ProfileData()
        }
    }

    override val authenticationService: AuthenticationService
        get() = TODO("Not yet implemented")
}
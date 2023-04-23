package com.example.testfordatabase.user

import com.example.testfordatabase.ProfileService
import com.example.testfordatabase.UserNotFoundException
import com.example.testfordatabase.swagger.api.ProfileResponseData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.function.Supplier

fun <T> ok(body: T): ResponseEntity<T>? {
    return ResponseEntity(body, HttpStatus.OK)
}

@RestController
@RequestMapping("/customers6")
class ProfilesController(
    private val userRepository: UserRepository
) {
    @GetMapping("/profiles/{username}")
    fun getProfileByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
        print("buuuka")
      return  userRepository.findByUsername(username)?.map {
                ok(toProfileResponse(it, false))
        } ?.orElseThrow<UserNotFoundException>(Supplier {
            UserNotFoundException(
                username
            )
        })
    }

    fun toProfileResponse(myUser: MyUser?, isFollowing: Boolean): ProfileResponseData? {
        val profileResponse = ProfileResponseData()
        val profile = ProfileService.toProfile(myUser, isFollowing)
        profileResponse.profile = profile
        return profileResponse
    }
}
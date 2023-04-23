package com.example.testfordatabase.swagger.api

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.NativeWebRequest
import java.util.*
import javax.annotation.Generated

/**
 * A delegate to be called by the [ProfilesApiController]}.
 * Implement this interface with a [org.springframework.stereotype.Service] annotated class.
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
interface ProfilesApiDelegate {
    val request: Optional<NativeWebRequest>
        get() = Optional.empty()

    /**
     * POST /profiles/{username}/follow : Follow a user
     * Follow a user by username
     *
     * @param username Username of the profile you want to follow (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     * @see ProfilesApi.followUserByUsername
     */
    fun followUserByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
        request.ifPresent { request: NativeWebRequest ->
            for (mediaType in MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    val exampleString =
                        "{ \"profile\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" } }"
                    ApiUtil.setExampleResponse(request, "application/json", exampleString)
                    break
                }
            }
        }
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    /**
     * GET /profiles/{username} : Get a profile
     * Get a profile of a user of the system. Auth is optional
     *
     * @param username Username of the profile to get (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     * @see ProfilesApi.getProfileByUsername
     */
    fun getProfileByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
        request.ifPresent { request: NativeWebRequest ->
            for (mediaType in MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    val exampleString =
                        "{ \"profile\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" } }"
                    ApiUtil.setExampleResponse(request, "application/json", exampleString)
                    break
                }
            }
        }
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    /**
     * DELETE /profiles/{username}/follow : Unfollow a user
     * Unfollow a user by username
     *
     * @param username Username of the profile you want to unfollow (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     * @see ProfilesApi.unfollowUserByUsername
     */
    fun unfollowUserByUsername(username: String?): ResponseEntity<ProfileResponseData?>? {
        request.ifPresent { request: NativeWebRequest ->
            for (mediaType in MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    val exampleString =
                        "{ \"profile\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" } }"
                    ApiUtil.setExampleResponse(request, "application/json", exampleString)
                    break
                }
            }
        }
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
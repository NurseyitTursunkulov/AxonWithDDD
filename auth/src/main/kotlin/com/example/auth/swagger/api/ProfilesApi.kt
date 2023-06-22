/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.1.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.auth.swagger.api

import com.example.testfordatabase.swagger.api.ProfileResponseData
import io.swagger.annotations.*
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.annotation.Generated

@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
@Validated
@Api(value = "profiles", description = "the profiles API")
interface ProfilesApi {
    val delegate: ProfilesApiDelegate
        get() = object : ProfilesApiDelegate {}

    /**
     * POST /profiles/{username}/follow : Follow a user
     * Follow a user by username
     *
     * @param username Username of the profile you want to follow (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     */
    @ApiOperation(
        value = "Follow a user",
        nickname = "followUserByUsername",
        notes = "Follow a user by username",
        response = ProfileResponseData::class,
        authorizations = [Authorization(value = "Token")],
        tags = ["Profile"]
    )
    @ApiResponses(
        value = [ApiResponse(code = 200, message = "OK", response = ProfileResponseData::class), ApiResponse(
            code = 401,
            message = "Unauthorized"
        ), ApiResponse(code = 422, message = "Unexpected error", response = GenericErrorModelData::class)]
    )
    @PostMapping(value = ["/profiles/{username}/follow"], produces = ["application/json"])
    fun followUserByUsername(
        @ApiParam(
            value = "Username of the profile you want to follow",
            required = true
        ) @PathVariable("username") username: String?
    ): ResponseEntity<ProfileResponseData?>? {
        return delegate.followUserByUsername(username)
    }

    /**
     * GET /profiles/{username} : Get a profile
     * Get a profile of a user of the system. Auth is optional
     *
     * @param username Username of the profile to get (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     */
    @ApiOperation(
        value = "Get a profile",
        nickname = "getProfileByUsername",
        notes = "Get a profile of a user of the system. Auth is optional",
        response = ProfileResponseData::class,
        tags = ["Profile"]
    )
    @ApiResponses(
        value = [ApiResponse(code = 200, message = "OK", response = ProfileResponseData::class), ApiResponse(
            code = 401,
            message = "Unauthorized"
        ), ApiResponse(code = 422, message = "Unexpected error", response = GenericErrorModelData::class)]
    )
    @GetMapping(value = ["/profiles/{username}"], produces = ["application/json"])
    fun getProfileByUsername(
        @ApiParam(
            value = "Username of the profile to get",
            required = true
        ) @PathVariable("username") username: String?
    ): ResponseEntity<ProfileResponseData?>? {
        return delegate.getProfileByUsername(username)
    }

    /**
     * DELETE /profiles/{username}/follow : Unfollow a user
     * Unfollow a user by username
     *
     * @param username Username of the profile you want to unfollow (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     */
    @ApiOperation(
        value = "Unfollow a user",
        nickname = "unfollowUserByUsername",
        notes = "Unfollow a user by username",
        response = ProfileResponseData::class,
        authorizations = [Authorization(value = "Token")],
        tags = ["Profile"]
    )
    @ApiResponses(
        value = [ApiResponse(code = 200, message = "OK", response = ProfileResponseData::class), ApiResponse(
            code = 401,
            message = "Unauthorized"
        ), ApiResponse(code = 422, message = "Unexpected error", response = GenericErrorModelData::class)]
    )
    @DeleteMapping(value = ["/profiles/{username}/follow"], produces = ["application/json"])
    fun unfollowUserByUsername(
        @ApiParam(
            value = "Username of the profile you want to unfollow",
            required = true
        ) @PathVariable("username") username: String?
    ): ResponseEntity<ProfileResponseData?>? {
        return delegate.unfollowUserByUsername(username)
    }
}
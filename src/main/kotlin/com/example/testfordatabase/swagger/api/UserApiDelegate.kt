package com.example.testfordatabase.swagger.api

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.NativeWebRequest
import java.util.*
import javax.annotation.Generated

/**
 * A delegate to be called by the [UserApiController]}.
 * Implement this interface with a [org.springframework.stereotype.Service] annotated class.
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
interface UserApiDelegate {
    val request: Optional<NativeWebRequest>
        get() = Optional.empty()
    val currentUser: ResponseEntity<UserResponseData?>?
        /**
         * GET /user : Get current user
         * Gets the currently logged-in user
         *
         * @return OK (status code 200)
         * or Unauthorized (status code 401)
         * or Unexpected error (status code 422)
         * @see UserApi.getCurrentUser
         */
        get() {
            request.ifPresent { request: NativeWebRequest ->
                for (mediaType in MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        val exampleString =
                            "{ \"user\" : { \"image\" : \"image\", \"bio\" : \"bio\", \"email\" : \"email\", \"token\" : \"token\", \"username\" : \"username\" } }"
                        ApiUtil.setExampleResponse(request, "application/json", exampleString)
                        break
                    }
                }
            }
            return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
        }

    /**
     * PUT /user : Update current user
     * Updated user information for current user
     *
     * @param body User details to update. At least **one** field is required. (required)
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     * @see UserApi.updateCurrentUser
     */
    fun updateCurrentUser(body: UpdateUserRequestData?): ResponseEntity<UserResponseData?>? {
        request.ifPresent { request: NativeWebRequest ->
            for (mediaType in MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    val exampleString =
                        "{ \"user\" : { \"image\" : \"image\", \"bio\" : \"bio\", \"email\" : \"email\", \"token\" : \"token\", \"username\" : \"username\" } }"
                    ApiUtil.setExampleResponse(request, "application/json", exampleString)
                    break
                }
            }
        }
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
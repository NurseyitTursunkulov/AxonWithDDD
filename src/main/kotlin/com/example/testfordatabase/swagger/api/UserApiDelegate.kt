package com.example.testfordatabase.swagger.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

/**
 * A delegate to be called by the {@link UserApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
public interface UserApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /user : Get current user
     * Gets the currently logged-in user
     *
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Unexpected error (status code 422)
     * @see UserApi#getCurrentUser
     */
    default ResponseEntity<UserResponseData> getCurrentUser() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"user\" : { \"image\" : \"image\", \"bio\" : \"bio\", \"email\" : \"email\", \"token\" : \"token\", \"username\" : \"username\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /user : Update current user
     * Updated user information for current user
     *
     * @param body User details to update. At least **one** field is required. (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Unexpected error (status code 422)
     * @see UserApi#updateCurrentUser
     */
    default ResponseEntity<UserResponseData> updateCurrentUser(UpdateUserRequestData body) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"user\" : { \"image\" : \"image\", \"bio\" : \"bio\", \"email\" : \"email\", \"token\" : \"token\", \"username\" : \"username\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

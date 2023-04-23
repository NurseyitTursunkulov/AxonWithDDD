package com.example.testfordatabase.swagger.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

/**
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
public interface UsersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users : Register a new user
     * Register a new user
     *
     * @param body Details of the new user to register (required)
     * @return OK (status code 201)
     *         or Unexpected error (status code 422)
     * @see UsersApi#createUser
     */
    default ResponseEntity<UserResponseData> createUser(NewUserRequestData body) {
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
     * POST /users/login : Existing user login
     * Login for existing user
     *
     * @param body Credentials to use (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Unexpected error (status code 422)
     * @see UsersApi#login
     */
    default ResponseEntity<UserResponseData> login(LoginUserRequestData body) {
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

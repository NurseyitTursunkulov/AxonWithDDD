package com.example.testfordatabase.swagger.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
@RestController
@RequestMapping("/api")
public class ProfilesApiController implements ProfilesApi {

    private final ProfilesApiDelegate delegate;

    public ProfilesApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) ProfilesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ProfilesApiDelegate() {});
    }

    @Override
    public ProfilesApiDelegate getDelegate() {
        return delegate;
    }

}

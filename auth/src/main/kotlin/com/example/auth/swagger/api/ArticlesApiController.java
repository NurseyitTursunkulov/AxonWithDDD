package com.example.auth.swagger.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.conduit.base-path:/api}")
public class ArticlesApiController implements ArticlesApi {

    private final ArticlesApiDelegate delegate;

    public ArticlesApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) ArticlesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ArticlesApiDelegate() {});
    }

    @Override
    public ArticlesApiDelegate getDelegate() {
        return delegate;
    }

}

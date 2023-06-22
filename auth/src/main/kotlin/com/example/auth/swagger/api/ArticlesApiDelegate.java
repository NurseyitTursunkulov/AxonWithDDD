package com.example.auth.swagger.api;

import com.example.articles.swagger.api.ArticlesApiController;
import com.example.testfordatabase.swagger.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

/**
 * A delegate to be called by the {@link ArticlesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
public interface ArticlesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }


    default ResponseEntity<SingleArticleResponseData> createArticle(NewArticleRequestData article) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"article\" : { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    default ResponseEntity<SingleCommentResponseData> createArticleComment(String slug,
                                                                           NewCommentRequestData comment) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"comment\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"id\" : 0, \"body\" : \"body\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    default ResponseEntity<SingleArticleResponseData> createArticleFavorite(String slug) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"article\" : { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    default ResponseEntity<Void> deleteArticle(String slug) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    default ResponseEntity<Void> deleteArticleComment(String slug,
        Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    default ResponseEntity<SingleArticleResponseData> deleteArticleFavorite(String slug) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"article\" : { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    default ResponseEntity<SingleArticleResponseData> getArticle(String slug) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"article\" : { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    default ResponseEntity<MultipleCommentsResponseData> getArticleComments(String slug) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"comments\" : [ { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"id\" : 0, \"body\" : \"body\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" }, { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"id\" : 0, \"body\" : \"body\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    default ResponseEntity<MultipleArticlesResponseData> getArticles(String tag,
        String author,
        String favorited,
        Integer limit,
        Integer offset) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"articlesCount\" : 6, \"articles\" : [ { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true }, { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
    default ResponseEntity<MultipleArticlesResponseData> getArticlesFeed(Integer limit,
        Integer offset) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"articlesCount\" : 6, \"articles\" : [ { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true }, { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /articles/{slug} : Update an article
     * Update an article. Auth is required
     *
     * @param slug Slug of the article to update (required)
     * @param article Article to update (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Unexpected error (status code 422)
     * @see ArticlesApi#updateArticle
     */
    default ResponseEntity<SingleArticleResponseData> updateArticle(String slug,
        UpdateArticleRequestData article) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"article\" : { \"tagList\" : [ \"tagList\", \"tagList\" ], \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"author\" : { \"image\" : \"image\", \"following\" : true, \"bio\" : \"bio\", \"username\" : \"username\" }, \"description\" : \"description\", \"title\" : \"title\", \"body\" : \"body\", \"favoritesCount\" : 0, \"slug\" : \"slug\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\", \"favorited\" : true } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

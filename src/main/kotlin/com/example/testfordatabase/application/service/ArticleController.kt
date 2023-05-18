package com.example.testfordatabase.application.service

import com.example.testfordatabase.application.dto.mapper.FavouriteInfo
import com.example.testfordatabase.application.dto.mapper.fromNewArticleData
import com.example.testfordatabase.application.dto.mapper.toSingleArticleResponse
import com.example.testfordatabase.application.exception.ArticleNotFoundException
import com.example.testfordatabase.application.util.BaseService
import com.example.testfordatabase.domain.aggregate.article.Article
import com.example.testfordatabase.domain.aggregate.article.ArticleRepository
import com.example.testfordatabase.domain.aggregate.comment.CommentRepository
import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavourite
import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavouriteId
import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavouriteRepository
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationId
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationRepository
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.service.AuthenticationService
import com.example.testfordatabase.swagger.api.NewArticleRequestData
import com.example.testfordatabase.swagger.api.SingleArticleResponseData
import com.example.testfordatabase.swagger.api.UpdateArticleData
import com.example.testfordatabase.swagger.api.UpdateArticleRequestData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@Service
@Transactional
@RestController
class ArticleController
@Autowired constructor(
    private val articleRepository: ArticleRepository,
            private val followRelationRepository: FollowRelationRepository,
            private val articleFavouriteRepository: ArticleFavouriteRepository,
            private val commentRepository: CommentRepository,
             override val authenticationService: AuthenticationService
) : BaseService(){
    @GetMapping("createArticle")
    fun createArticle(@RequestBody req:NewArticleRequestData): ResponseEntity<SingleArticleResponseData?>? {
        val currentUser = currentUserOrThrow()
        val newArticleData = req.article
        val article =  fromNewArticleData(newArticleData, currentUser)
        articleRepository.save(article)
        return articleResponse(article)
    }

    @GetMapping("getArticle/{slug}")
    fun getArticle(@PathVariable("slug") slug: String?): ResponseEntity<SingleArticleResponseData?>? {
        return articleRepository
            .findBySlug(slug)
            ?.let { article: Article -> articleResponse(article) }
            ?: throw ArticleNotFoundException(slug)
    }

    @GetMapping("updateArticle/{slug}")
    fun updateArticle(
        @PathVariable("slug") slug: String?, @RequestBody req: UpdateArticleRequestData
    ): ResponseEntity<SingleArticleResponseData?>? {
        return articleRepository
            .findBySlug(slug)
            ?.let { article ->
                val updateArticleData: UpdateArticleData = req.article
                com.example.testfordatabase.application.dto.mapper.updateArticle(article, updateArticleData)
                articleRepository.save(article)
                articleResponse(article)
            }
            ?:run{ throw ArticleNotFoundException(slug) }
    }
    @DeleteMapping("deleteArticle/{slug}")
    fun deleteArticle(@PathVariable("slug") slug: String?): ResponseEntity<Unit> {
        articleRepository
            .findBySlug(slug)
            ?.let { article ->
                commentRepository.deleteByArticleId(article.id)
                articleRepository.delete(article)
            }
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("createArticleFavorite/{slug}")
    fun createArticleFavorite(@PathVariable("slug") slug: String?): ResponseEntity<SingleArticleResponseData?>? {
        val currentUser: MyUser = currentUserOrThrow()
        return articleRepository
            .findBySlug(slug)
            ?.let { article ->
                val favId = ArticleFavouriteId(currentUser.id, article.id)
                articleFavouriteRepository
                    .findById(favId)
                    .orElseGet {
                        val fav = ArticleFavourite(currentUser.id, article.id)
                        articleFavouriteRepository.save(fav)
                    }
                articleResponse(article)
            }
            ?:run {throw ArticleNotFoundException(slug) }
    }
    @DeleteMapping("deleteArticleFavorite/{slug}")
    fun deleteArticleFavorite(@PathVariable("slug") slug: String?): ResponseEntity<SingleArticleResponseData?>? {
        val currentUser: MyUser = currentUserOrThrow()
        return articleRepository
            .findBySlug(slug)
            ?.let { article ->
                val favId = ArticleFavouriteId(currentUser.id, article.id)
                articleFavouriteRepository.deleteById(favId)
                articleResponse(article)
            }
            ?:run { throw ArticleNotFoundException(slug) }
    }

    private fun articleResponse(article: Article): ResponseEntity<SingleArticleResponseData?>{
        val isFollowingAuthor: Boolean = isFollowingAuthor(article)
        val isFavoured = authenticationService
            .currentMyUser?.let { currentUser ->
                articleFavouriteRepository
                    .findById(ArticleFavouriteId(currentUser.id, article.id))
                    .isPresent
            }?:false
        val favouritesCount = articleFavouriteRepository.countByIdArticleId(article.id)
        val favouriteInfo = FavouriteInfo(isFavoured, favouritesCount)
        return ok(toSingleArticleResponse(article, favouriteInfo, isFollowingAuthor))
    }
    private fun isFollowingAuthor(article: Article): Boolean {
        return authenticationService
            .currentMyUser
            ?.let { currentUser ->
                followRelationRepository
                    .findById(
                        FollowRelationId(currentUser.id, article.author.id)
                    )
                    .isPresent
            }?:false
    }
}
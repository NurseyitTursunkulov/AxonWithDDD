package com.example.testfordatabase.application.service

import com.example.testfordatabase.application.dto.mapper.*
import com.example.testfordatabase.application.exception.ArticleNotFoundException
import com.example.testfordatabase.application.util.BaseService
import com.example.testfordatabase.domain.aggregate.article.Article
import com.example.testfordatabase.domain.aggregate.article.ArticleRepository
import com.example.testfordatabase.domain.aggregate.article.OffsetBasedPageRequest
import com.example.testfordatabase.domain.aggregate.comment.Comment
import com.example.testfordatabase.domain.aggregate.comment.CommentRepository
import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavourite
import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavouriteId
import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavouriteRepository
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationId
import com.example.testfordatabase.domain.aggregate.follow.FollowRelationRepository
import com.example.testfordatabase.domain.aggregate.user.MyUser
import com.example.testfordatabase.domain.service.AuthenticationService
import com.example.testfordatabase.swagger.api.*
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
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
    @GetMapping("createArticleComment/{slug}")
    fun createArticleComment(
        @PathVariable("slug") slug: String, @RequestBody commentData: NewCommentRequestData
    ): ResponseEntity<SingleCommentResponseData?>? {
        val currentUser: MyUser = currentUserOrThrow()
        return articleRepository
            .findBySlug(slug)?.let { article:Article ->
                val isFollowingAuthor = isFollowingAuthor(article)
                val comment: Comment = fromNewCommentData(commentData.comment, article, currentUser)
                ok(
                    toSingleCommentResponseData(
                        commentRepository.save(comment), isFollowingAuthor
                    )
                )
            }
            ?:run {throw ArticleNotFoundException(slug) }
    }

    @DeleteMapping("deleteArticleComment/{slug}/{id}")
    fun deleteArticleComment(  @PathVariable("slug") slug: String?,  @PathVariable("id")  id: Int): ResponseEntity<Void?>? {
        commentRepository.deleteById(id.toLong())
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("getArticleComments/{slug}")
    fun getArticleComments( @PathVariable("slug") slug: String?): ResponseEntity<MultipleCommentsResponseData?>? {
        return articleRepository
            .findBySlug(slug)
            ?.let { article ->
                val comments: List<Comment>? = commentRepository.findByArticleId(article.id)
                ok(toMultipleCommentsResponseData(comments, followingIds()))
            }
            ?:run { throw ArticleNotFoundException(slug) }
    }
    @GetMapping("articles/feed/{limit}/{offset}")
    fun getArticlesFeed(
        @PathVariable("limit")  limit: Int = 20, @PathVariable("offset")  offset: Int = 0
    ): ResponseEntity<MultipleArticlesResponseData?>? {
        val followingIds = followingIds()
        val articles: List<Article> = articleRepository.findByAuthorIdIn(
            followingIds,
            OffsetBasedPageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "createdAt"))
        )?: emptyList()
        val articleCount: Int = articleRepository.countByAuthorIdIn(followingIds)
        return articlesResponse(articles, articleCount)
    }

    @GetMapping("getArticles/{tag}/{author}/{favorited}/{limit}/{offset}")
    fun getArticles(
        @PathVariable("tag")  tag: String,@PathVariable("author")  author: String,@PathVariable("favorited")  favorited: String,  @PathVariable("limit") limit: Int,@PathVariable("offset")  offset: Int
    ): ResponseEntity<MultipleArticlesResponseData?>? {
        val articles:  List<Article> = articleRepository.findByFilters(
            tag,
            author,
            favorited,
            OffsetBasedPageRequest.of(
                offset,
                limit, Sort.by(Sort.Direction.DESC, "createdAt")
            )
        )
        val articleCount: Int = articleRepository.countByFilter(tag, author, favorited)
        return articlesResponse(articles, articleCount)
    }

    @GetMapping("tagsGet")
    fun tagsGet(): ResponseEntity<TagsResponseData?>? {
        return ok(toTagsResponseData(articleRepository.findAllTags()))
    }
    private fun articlesResponse(
        articles: List<Article>, articleCount: Int
    ): ResponseEntity<MultipleArticlesResponseData?> {
        val articleIds = articles.map { it.id }
        val favouritedCounts = articleFavouriteRepository.countByIdArticleIds(articleIds)
            ?.groupBy { it.articleId }
            ?.mapValues { it.value.size.toLong() }?: mapOf()


        val favourited:Set<Long> =    authenticationService
            .currentMyUser?.let { currentUser ->
                articleFavouriteRepository.findByIdUserId(currentUser.id)?.map { articleFavourite ->
                    articleFavourite.id.articleId
                }?.toSet()
            }
            ?: setOf<Long>()
        val favouriteInfo =  MultipleFavouriteInfo(favourited, favouritedCounts)
        return ok(
            toMultipleArticlesResponseData(
                articles, favouriteInfo, followingIds(), articleCount
            )
        )
    }


    private fun followingIds(): Set<Long> {
        return authenticationService
            .currentMyUser
            ?.let { currentUser ->
                followRelationRepository.findByIdFollowerId(currentUser.id)?.map { f -> f.id.followeeId }?.toSet()
            }
            ?:(emptySet())
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
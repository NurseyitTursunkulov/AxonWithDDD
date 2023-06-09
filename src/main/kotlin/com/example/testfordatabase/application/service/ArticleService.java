//package com.example.testfordatabase.application.service;
//
//import com.example.testfordatabase.application.exception.ArticleNotFoundException;
//import com.example.testfordatabase.application.util.BaseService;
//import com.example.testfordatabase.domain.aggregate.article.Article;
//import com.example.testfordatabase.domain.aggregate.article.ArticleRepository;
//import com.example.testfordatabase.domain.aggregate.comment.CommentRepository;
//import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavourite;
//import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavouriteId;
//import com.example.testfordatabase.domain.aggregate.favorite.ArticleFavouriteRepository;
//import com.example.testfordatabase.domain.aggregate.follow.FollowRelationId;
//import com.example.testfordatabase.domain.aggregate.follow.FollowRelationRepository;
//import com.example.testfordatabase.domain.service.AuthenticationService;
//import com.example.testfordatabase.swagger.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.request.NativeWebRequest;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static com.example.testfordatabase.application.dto.mapper.MappersKt.fromNewArticleData;
//
//@Service
//@Transactional
//public class ArticleService extends BaseService implements ArticlesApiDelegate, TagsApiDelegate {
//  private final ArticleRepository articleRepository;
//  private final FollowRelationRepository followRelationRepository;
//  private final ArticleFavouriteRepository articleFavouriteRepository;
//  private final CommentRepository commentRepository;
//  private final AuthenticationService authenticationService;
//
//  /** Creates ArticleService instance. */
//  @SuppressWarnings("PMD.ExcessiveParameterList")
//  @Autowired
//  public ArticleService(
//      ArticleRepository articleRepository,
//      FollowRelationRepository followRelationRepository,
//      ArticleFavouriteRepository articleFavouriteRepository,
//      CommentRepository commentRepository,
//      AuthenticationService authenticationService) {
//    this.articleRepository = articleRepository;
//    this.followRelationRepository = followRelationRepository;
//    this.articleFavouriteRepository = articleFavouriteRepository;
//    this.commentRepository = commentRepository;
//    this.authenticationService = authenticationService;
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public Optional<NativeWebRequest> getRequest() {
//    return Optional.empty();
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<SingleArticleResponseData> createArticle(NewArticleRequestData req) {
//    final var currentUser = currentUserOrThrow();
//
//    final var newArticleData = req.getArticle();
//    final var article = fromNewArticleData(newArticleData, currentUser);
////    articleRepository.save(article);
//
//    return articleResponse(article);
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<SingleArticleResponseData> getArticle(String slug) {
//    return articleRepository
//        .findBySlug(slug)
//        .map(this::articleResponse)
//        .orElseThrow(() -> new ArticleNotFoundException(slug));
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<SingleArticleResponseData> updateArticle(
//      String slug, UpdateArticleRequestData req) {
//    return articleRepository
//        .findBySlug(slug)
//        .map(
//            article -> {
//              final var updateArticleData = req.getArticle();
//              updateArticle(article, updateArticleData);
//              articleRepository.save(article);
//              return articleResponse(article);
//            })
//        .orElseThrow(() -> new ArticleNotFoundException(slug));
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<Void> deleteArticle(String slug) {
//    articleRepository
//        .findBySlug(slug)
//        .ifPresent(
//            article -> {
//              commentRepository.deleteByArticleId(article.getId());
//              articleRepository.delete(article);
//            });
//    return new ResponseEntity<>(HttpStatus.OK);
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<SingleArticleResponseData> createArticleFavorite(String slug) {
//    final var currentUser = currentUserOrThrow();
//
//    return articleRepository
//        .findBySlug(slug)
//        .map(
//            article -> {
//              final var favId = new ArticleFavouriteId(currentUser.getId(), article.getId());
//              articleFavouriteRepository
//                  .findById(favId)
//                  .orElseGet(
//                      () -> {
//                        final var fav = new ArticleFavourite(currentUser.getId(), article.getId());
//                        return articleFavouriteRepository.save(fav);
//                      });
//              return articleResponse(article);
//            })
//        .orElseThrow(() -> new ArticleNotFoundException(slug));
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<SingleArticleResponseData> deleteArticleFavorite(String slug) {
//    final var currentUser = currentUserOrThrow();
//    return articleRepository
//        .findBySlug(slug)
//        .map(
//            article -> {
//              final var favId = new ArticleFavouriteId(currentUser.getId(), article.getId());
//              articleFavouriteRepository.deleteById(favId);
//              return articleResponse(article);
//            })
//        .orElseThrow(() -> new ArticleNotFoundException(slug));
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<SingleCommentResponseData> createArticleComment(
//      String slug, NewCommentRequestData commentData) {
//    final var currentUser = currentUserOrThrow();
//    return articleRepository
//        .findBySlug(slug)
//        .map(
//            article -> {
//              final var isFollowingAuthor = isFollowingAuthor(article);
//              final var comment =
//                  fromNewCommentData(commentData.getComment(), article, currentUser);
//              return ok(
//                  toSingleCommentResponseData(
//                      commentRepository.save(comment), isFollowingAuthor));
//            })
//        .orElseThrow(() -> new ArticleNotFoundException(slug));
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<Void> deleteArticleComment(String slug, Integer id) {
//    commentRepository.deleteById(id.longValue());
//    return new ResponseEntity<>(HttpStatus.OK);
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<MultipleCommentsResponseData> getArticleComments(String slug) {
//    return articleRepository
//        .findBySlug(slug)
//        .map(
//            article -> {
//              final var comments = commentRepository.findByArticleId(article.getId());
//              return ok(toMultipleCommentsResponseData(comments, followingIds()));
//            })
//        .orElseThrow(() -> new ArticleNotFoundException(slug));
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<MultipleArticlesResponseData> getArticlesFeed(
//      Integer limit, Integer offset) {
//    final var followingIds = followingIds();
//    final var articles =
//        articleRepository.findByAuthorIdIn(
//            followingIds,
//            OffsetBasedPageRequest.of(offset, limit, Sort.by(Direction.DESC, "createdAt")));
//    final var articleCount = articleRepository.countByAuthorIdIn(followingIds);
//    return articlesResponse(articles, articleCount);
//  }
//
//  /** {@inheritDoc} */
//  @SuppressWarnings("PMD.ExcessiveParameterList")
//  @Override
//  public ResponseEntity<MultipleArticlesResponseData> getArticles(
//      String tag, String author, String favorited, Integer limit, Integer offset) {
//    final var articles =
//        articleRepository.findByFilters(
//            tag,
//            author,
//            favorited,
//            OffsetBasedPageRequest.of(offset, limit, Sort.by(Direction.DESC, "createdAt")));
//    final var articleCount = articleRepository.countByFilter(tag, author, favorited);
//    return articlesResponse(articles, articleCount);
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public ResponseEntity<TagsResponseData> tagsGet() {
//    return ok(toTagsResponseData(articleRepository.findAllTags()));
//  }
//
//  private ResponseEntity<MultipleArticlesResponseData> articlesResponse(
//          List<Article> articles, int articleCount) {
//    final var articleIds = articles.stream().map(Article::getId).collect(Collectors.toList());
//    final var favouritedCounts =
//        articleFavouriteRepository.countByIdArticleIds(articleIds).stream()
//            .collect(Collectors.groupingBy(FavouriteCount::getArticleId, Collectors.counting()));
//    final var favourited =
//        getAuthenticationService()
//            .getCurrentUser()
//            .map(
//                currentUser ->
//                    articleFavouriteRepository.findByIdUserId(currentUser.getId()).stream()
//                        .map(f -> f.getId().getArticleId())
//                        .collect(Collectors.toSet()))
//            .orElse(Collections.emptySet());
//    final var favouriteInfo = new MultipleFavouriteInfo(favourited, favouritedCounts);
//
//    return ok(
//        toMultipleArticlesResponseData(
//            articles, favouriteInfo, followingIds(), articleCount));
//  }
//
//  private ResponseEntity<SingleArticleResponseData> articleResponse(Article article) {
//    final var isFollowingAuthor = isFollowingAuthor(article);
//    final var isFavoured =
//        getAuthenticationService()
//            .getCurrentUser()
//            .map(
//                currentUser ->
//                    articleFavouriteRepository
//                        .findById(new ArticleFavouriteId(currentUser.getId(), article.getId()))
//                        .isPresent())
//            .orElse(false);
//    final var favouritesCount = articleFavouriteRepository.countByIdArticleId(article.getId());
//    final var favouriteInfo = new FavouriteInfo(isFavoured, favouritesCount);
//    return ok(toSingleArticleResponse(article, favouriteInfo, isFollowingAuthor));
//  }
//
//  private boolean isFollowingAuthor(Article article) {
//    return getAuthenticationService()
//        .getCurrentUser()
//        .map(
//            currentUser ->
//                followRelationRepository
//                    .findById(
//                        new FollowRelationId(currentUser.getId(), article.getAuthor().getId()))
//                    .isPresent())
//        .orElse(false);
//  }
//
//  private Set<Long> followingIds() {
//    return getAuthenticationService()
//        .getCurrentUser()
//        .map(
//            currentUser ->
//                followRelationRepository.findByIdFollowerId(currentUser.getId()).stream()
//                    .map(f -> f.getId().getFolloweeId())
//                    .collect(Collectors.toSet()))
//        .orElse(Collections.emptySet());
//  }
//
//  /** {@inheritDoc} */
//  @Override
//  public AuthenticationService getAuthenticationService() {
//    return authenticationService;
//  }
//}

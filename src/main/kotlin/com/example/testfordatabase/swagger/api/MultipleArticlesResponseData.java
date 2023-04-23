package com.example.testfordatabase.swagger.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MultipleArticlesResponseData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
public class MultipleArticlesResponseData   {
  @JsonProperty("articles")
  @Valid
  private List<ArticleData> articles = new ArrayList<>();

  @JsonProperty("articlesCount")
  private Integer articlesCount;

  public MultipleArticlesResponseData articles(List<ArticleData> articles) {
    this.articles = articles;
    return this;
  }

  public MultipleArticlesResponseData addArticlesItem(ArticleData articlesItem) {
    this.articles.add(articlesItem);
    return this;
  }

  /**
   * Get articles
   * @return articles
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<ArticleData> getArticles() {
    return articles;
  }

  public void setArticles(List<ArticleData> articles) {
    this.articles = articles;
  }

  public MultipleArticlesResponseData articlesCount(Integer articlesCount) {
    this.articlesCount = articlesCount;
    return this;
  }

  /**
   * Get articlesCount
   * @return articlesCount
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getArticlesCount() {
    return articlesCount;
  }

  public void setArticlesCount(Integer articlesCount) {
    this.articlesCount = articlesCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MultipleArticlesResponseData multipleArticlesResponse = (MultipleArticlesResponseData) o;
    return Objects.equals(this.articles, multipleArticlesResponse.articles) &&
        Objects.equals(this.articlesCount, multipleArticlesResponse.articlesCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articles, articlesCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultipleArticlesResponseData {\n");
    
    sb.append("    articles: ").append(toIndentedString(articles)).append("\n");
    sb.append("    articlesCount: ").append(toIndentedString(articlesCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


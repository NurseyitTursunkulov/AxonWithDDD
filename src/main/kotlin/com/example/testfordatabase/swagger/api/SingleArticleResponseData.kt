package com.example.testfordatabase.swagger.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

/**
 * SingleArticleResponseData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
public class SingleArticleResponseData   {
  @JsonProperty("article")
  private ArticleData article;

  public SingleArticleResponseData article(ArticleData article) {
    this.article = article;
    return this;
  }

  /**
   * Get article
   * @return article
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public ArticleData getArticle() {
    return article;
  }

  public void setArticle(ArticleData article) {
    this.article = article;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SingleArticleResponseData singleArticleResponse = (SingleArticleResponseData) o;
    return Objects.equals(this.article, singleArticleResponse.article);
  }

  @Override
  public int hashCode() {
    return Objects.hash(article);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SingleArticleResponseData {\n");
    
    sb.append("    article: ").append(toIndentedString(article)).append("\n");
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


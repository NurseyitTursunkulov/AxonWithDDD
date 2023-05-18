package com.example.testfordatabase.swagger.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MultipleCommentsResponseData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
public class MultipleCommentsResponseData   {
  @JsonProperty("comments")
  @Valid
  private List<CommentData> comments = new ArrayList<>();

  public MultipleCommentsResponseData comments(List<CommentData> comments) {
    this.comments = comments;
    return this;
  }

  public MultipleCommentsResponseData addCommentsItem(CommentData commentsItem) {
    this.comments.add(commentsItem);
    return this;
  }

  /**
   * Get comments
   * @return comments
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<CommentData> getComments() {
    return comments;
  }

  public void setComments(List<CommentData> comments) {
    this.comments = comments;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MultipleCommentsResponseData multipleCommentsResponse = (MultipleCommentsResponseData) o;
    return Objects.equals(this.comments, multipleCommentsResponse.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultipleCommentsResponseData {\n");
    
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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


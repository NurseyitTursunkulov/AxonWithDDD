package com.example.auth.swagger.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GenericErrorModelErrorsData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]")
public class GenericErrorModelErrorsData   {
  @JsonProperty("body")
  @Valid
  private List<String> body = new ArrayList<>();

  public GenericErrorModelErrorsData body(List<String> body) {
    this.body = body;
    return this;
  }

  public GenericErrorModelErrorsData addBodyItem(String bodyItem) {
    this.body.add(bodyItem);
    return this;
  }

  /**
   * Get body
   * @return body
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public List<String> getBody() {
    return body;
  }

  public void setBody(List<String> body) {
    this.body = body;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenericErrorModelErrorsData genericErrorModelErrors = (GenericErrorModelErrorsData) o;
    return Objects.equals(this.body, genericErrorModelErrors.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenericErrorModelErrorsData {\n");
    
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
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


package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.OffsetDateTime
import java.util.*
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * ArticleData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
data class ArticleData(
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("slug")
    var slug: String? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("title")
    var title: String? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("description")
    var description: String? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("body")
    var body: String? = null,
    @JsonProperty("tagList")
     var tagList: @Valid MutableList<String>? = ArrayList(),
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var createdAt: OffsetDateTime? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("updatedAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var updatedAt: OffsetDateTime? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("favorited")
    var favorited: Boolean? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("favoritesCount")
    var favoritesCount: Int? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("author")
    var author: ProfileData? = null
) {

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ArticleData {\n")
        sb.append("    slug: ").append(toIndentedString(slug)).append("\n")
        sb.append("    title: ").append(toIndentedString(title)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
        sb.append("    body: ").append(toIndentedString(body)).append("\n")
        sb.append("    tagList: ").append(toIndentedString(tagList)).append("\n")
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n")
        sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n")
        sb.append("    favorited: ").append(toIndentedString(favorited)).append("\n")
        sb.append("    favoritesCount: ").append(toIndentedString(favoritesCount)).append("\n")
        sb.append("    author: ").append(toIndentedString(author)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}
package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.OffsetDateTime
import java.util.*
import javax.annotation.Generated

/**
 * CommentData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class CommentData {
    /**
     * Get id
     * @return id
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("id")
    var id: Int? = null

    /**
     * Get createdAt
     * @return createdAt
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var createdAt: OffsetDateTime? = null

    /**
     * Get updatedAt
     * @return updatedAt
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("updatedAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var updatedAt: OffsetDateTime? = null

    /**
     * Get body
     * @return body
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("body")
    var body: String? = null

    /**
     * Get author
     * @return author
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("author")
    var author: ProfileData? = null
    fun id(id: Int?): CommentData {
        this.id = id
        return this
    }

    fun createdAt(createdAt: OffsetDateTime?): CommentData {
        this.createdAt = createdAt
        return this
    }

    fun updatedAt(updatedAt: OffsetDateTime?): CommentData {
        this.updatedAt = updatedAt
        return this
    }

    fun body(body: String?): CommentData {
        this.body = body
        return this
    }

    fun author(author: ProfileData?): CommentData {
        this.author = author
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val comment = o as CommentData
        return id == comment.id && createdAt == comment.createdAt && updatedAt == comment.updatedAt && body == comment.body && author == comment.author
    }

    override fun hashCode(): Int {
        return Objects.hash(id, createdAt, updatedAt, body, author)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class CommentData {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n")
        sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n")
        sb.append("    body: ").append(toIndentedString(body)).append("\n")
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
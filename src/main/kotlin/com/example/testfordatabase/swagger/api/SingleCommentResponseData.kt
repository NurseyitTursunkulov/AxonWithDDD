package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * SingleCommentResponseData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class SingleCommentResponseData {
    /**
     * Get comment
     * @return comment
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("comment")
    var comment: CommentData? = null
    fun comment(comment: CommentData?): SingleCommentResponseData {
        this.comment = comment
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val singleCommentResponse = o as SingleCommentResponseData
        return comment == singleCommentResponse.comment
    }

    override fun hashCode(): Int {
        return Objects.hash(comment)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SingleCommentResponseData {\n")
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n")
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
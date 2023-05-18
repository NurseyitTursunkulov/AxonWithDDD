package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * NewCommentData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class NewCommentData {
    /**
     * Get body
     * @return body
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("body")
    var body: String? = null
    fun body(body: String?): NewCommentData {
        this.body = body
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val newComment = o as NewCommentData
        return body == newComment.body
    }

    override fun hashCode(): Int {
        return Objects.hash(body)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class NewCommentData {\n")
        sb.append("    body: ").append(toIndentedString(body)).append("\n")
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
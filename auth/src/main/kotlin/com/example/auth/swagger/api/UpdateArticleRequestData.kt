package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * UpdateArticleRequestData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
data class UpdateArticleRequestData(
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("article")
    var article: UpdateArticleData
) {

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class UpdateArticleRequestData {\n")
        sb.append("    article: ").append(toIndentedString(article)).append("\n")
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
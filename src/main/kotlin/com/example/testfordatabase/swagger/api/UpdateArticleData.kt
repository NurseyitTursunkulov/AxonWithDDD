package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * UpdateArticleData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
data class UpdateArticleData(
    @get:ApiModelProperty(value = "")
    @JsonProperty("title")
    var title: String,
    @get:ApiModelProperty(value = "")
    @JsonProperty("description")
    var description: String,
    @get:ApiModelProperty(value = "")
    @JsonProperty("body")
    var body: String
) {
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class UpdateArticleData {\n")
        sb.append("    title: ").append(toIndentedString(title)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
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
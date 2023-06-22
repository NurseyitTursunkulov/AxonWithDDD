package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * SingleArticleResponseData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class SingleArticleResponseData {
    /**
     * Get article
     * @return article
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("article")
    var article: ArticleData? = null
    fun article(article: ArticleData?): SingleArticleResponseData {
        this.article = article
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val singleArticleResponse = o as SingleArticleResponseData
        return article == singleArticleResponse.article
    }

    override fun hashCode(): Int {
        return Objects.hash(article)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SingleArticleResponseData {\n")
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
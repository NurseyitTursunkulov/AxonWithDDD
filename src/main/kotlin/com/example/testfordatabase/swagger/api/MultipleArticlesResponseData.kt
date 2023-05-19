package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * MultipleArticlesResponseData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class MultipleArticlesResponseData {
    @JsonProperty("articles")
    private var articles: @Valid MutableList<ArticleData>? = ArrayList()

    /**
     * Get articlesCount
     * @return articlesCount
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("articlesCount")
    var articlesCount: Int? = null
    fun articles(articles: List<ArticleData>?): MultipleArticlesResponseData {
        this.articles = articles?.toMutableList()
        return this
    }

    fun addArticlesItem(articlesItem: ArticleData): MultipleArticlesResponseData {
        articles!!.add(articlesItem)
        return this
    }

    /**
     * Get articles
     * @return articles
     */
    @ApiModelProperty(required = true, value = "")
    fun getArticles(): @NotNull @Valid MutableList<ArticleData>? {
        return articles
    }

    fun setArticles(articles: List<ArticleData>?) {
        this.articles = articles?.toMutableList()
    }

    fun articlesCount(articlesCount: Int?): MultipleArticlesResponseData {
        this.articlesCount = articlesCount
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val multipleArticlesResponse = o as MultipleArticlesResponseData
        return articles == multipleArticlesResponse.articles && articlesCount == multipleArticlesResponse.articlesCount
    }

    override fun hashCode(): Int {
        return Objects.hash(articles, articlesCount)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class MultipleArticlesResponseData {\n")
        sb.append("    articles: ").append(toIndentedString(articles)).append("\n")
        sb.append("    articlesCount: ").append(toIndentedString(articlesCount)).append("\n")
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
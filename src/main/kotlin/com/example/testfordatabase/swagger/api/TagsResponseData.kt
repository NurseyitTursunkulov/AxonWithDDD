package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * TagsResponseData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class TagsResponseData {
    @JsonProperty("tags")
    private var tags: @Valid MutableList<String>? = ArrayList()
    fun tags(tags: List<String>?): TagsResponseData {
        this.tags = tags?.toMutableList()
        return this
    }

    fun addTagsItem(tagsItem: String): TagsResponseData {
        tags!!.add(tagsItem)
        return this
    }

    /**
     * Get tags
     * @return tags
     */
    @ApiModelProperty(required = true, value = "")
    fun getTags(): @NotNull MutableList<String>? {
        return tags
    }

    fun setTags(tags: List<String>?) {
        this.tags = tags?.toMutableList()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tagsResponse = o as TagsResponseData
        return tags == tagsResponse.tags
    }

    override fun hashCode(): Int {
        return Objects.hash(tags)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class TagsResponseData {\n")
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n")
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
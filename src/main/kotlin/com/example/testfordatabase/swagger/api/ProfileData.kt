package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * ProfileData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
data class ProfileData(
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("username")
    var username: String? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("bio")
    var bio: String? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("image")
    var image: String? = null,
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("following")
    var following: Boolean? = null
) {
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ProfileData {\n")
        sb.append("    username: ").append(toIndentedString(username)).append("\n")
        sb.append("    bio: ").append(toIndentedString(bio)).append("\n")
        sb.append("    image: ").append(toIndentedString(image)).append("\n")
        sb.append("    following: ").append(toIndentedString(following)).append("\n")
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
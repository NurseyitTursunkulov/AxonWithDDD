package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * UpdateUserData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
data class UpdateUserData(
    @get:ApiModelProperty(value = "")
    @JsonProperty("email")
    var email: String? = null,

    @get:ApiModelProperty(value = "")
    @JsonProperty("token")
    var token: String? = null,

    @get:ApiModelProperty(value = "")
    @JsonProperty("username")
    var username: String? = null,

    @get:ApiModelProperty(value = "")
    @JsonProperty("bio")
    var bio: String? = null,

    @get:ApiModelProperty(value = "")
    @JsonProperty("image")
    var image: String? = null
) {


    fun email(email: String?): UpdateUserData {
        this.email = email
        return this
    }

    fun token(token: String?): UpdateUserData {
        this.token = token
        return this
    }

    fun username(username: String?): UpdateUserData {
        this.username = username
        return this
    }

    fun bio(bio: String?): UpdateUserData {
        this.bio = bio
        return this
    }

    fun image(image: String?): UpdateUserData {
        this.image = image
        return this
    }


    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class UpdateUserData {\n")
        sb.append("    email: ").append(toIndentedString(email)).append("\n")
        sb.append("    token: ").append(toIndentedString(token)).append("\n")
        sb.append("    username: ").append(toIndentedString(username)).append("\n")
        sb.append("    bio: ").append(toIndentedString(bio)).append("\n")
        sb.append("    image: ").append(toIndentedString(image)).append("\n")
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
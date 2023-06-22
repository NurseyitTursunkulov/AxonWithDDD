package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * UserData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class UserData {
    /**
     * Get email
     * @return email
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("email")
    var email: String? = null

    /**
     * Get token
     * @return token
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("token")
    var token: String? = null

    /**
     * Get username
     * @return username
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("username")
    var username: String? = null

    /**
     * Get bio
     * @return bio
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("bio")
    var bio: String? = null

    /**
     * Get image
     * @return image
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("image")
    var image: String? = null
    fun email(email: String?): UserData {
        this.email = email
        return this
    }

    fun token(token: String?): UserData {
        this.token = token
        return this
    }

    fun username(username: String?): UserData {
        this.username = username
        return this
    }

    fun bio(bio: String?): UserData {
        this.bio = bio
        return this
    }

    fun image(image: String?): UserData {
        this.image = image
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val user = o as UserData
        return email == user.email && token == user.token && username == user.username && bio == user.bio && image == user.image
    }

    override fun hashCode(): Int {
        return Objects.hash(email, token, username, bio, image)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class UserData {\n")
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
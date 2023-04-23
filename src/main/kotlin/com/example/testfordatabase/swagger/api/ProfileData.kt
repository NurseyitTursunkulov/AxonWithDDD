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
class ProfileData {
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

    /**
     * Get following
     * @return following
     */
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("following")
    var following: Boolean? = null
    fun username(username: String?): ProfileData {
        this.username = username
        return this
    }

    fun bio(bio: String?): ProfileData {
        this.bio = bio
        return this
    }

    fun image(image: String?): ProfileData {
        this.image = image
        return this
    }

    fun following(following: Boolean?): ProfileData {
        this.following = following
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val profile = o as ProfileData
        return username == profile.username && bio == profile.bio && image == profile.image && following == profile.following
    }

    override fun hashCode(): Int {
        return Objects.hash(username, bio, image, following)
    }

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
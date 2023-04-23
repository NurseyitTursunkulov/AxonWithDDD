package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * ProfileResponseData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
class ProfileResponseData {
    /**
     * Get profile
     * @return profile
     */
    @JvmField
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("profile")
    var profile: ProfileData? = null
    fun profile(profile: ProfileData?): ProfileResponseData {
        this.profile = profile
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val profileResponse = o as ProfileResponseData
        return profile == profileResponse.profile
    }

    override fun hashCode(): Int {
        return Objects.hash(profile)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ProfileResponseData {\n")
        sb.append("    profile: ").append(toIndentedString(profile)).append("\n")
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
package com.example.testfordatabase.swagger.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.annotation.Generated

/**
 * LoginUserRequestData
 */
@Generated(
    value = ["org.openapitools.codegen.languages.SpringCodegen"],
    date = "2023-03-19T14:53:52.454350+01:00[Europe/Berlin]"
)
data class LoginUserRequestData(
    @get:ApiModelProperty(required = true, value = "")
    @JsonProperty("user")
    var user: LoginUserData? = null
) {
    /**
     * Get user
     * @return user
     */

    fun user(user: LoginUserData?): LoginUserRequestData {
        this.user = user
        return this
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class LoginUserRequestData {\n")
        sb.append("    user: ").append(toIndentedString(user)).append("\n")
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
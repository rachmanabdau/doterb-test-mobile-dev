package com.example.doterb_belajar.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errors")
    val errors: List<Error>,
    @Json(name = "status")
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Error(
        @Json(name = "location")
        val location: String?,
        @Json(name = "msg")
        val msg: String,
        @Json(name = "param")
        val `param`: String?
    )
}
package com.example.doterb_belajar.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "message")
    val message: String,
    @Json(name = "result")
    val result: String,
    @Json(name = "status")
    val status: String
)
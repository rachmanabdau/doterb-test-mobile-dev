package com.example.doterb_belajar.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Belajar(
    @Json(name = "message")
    val message: String,
    @Json(name = "result")
    val result: Result,
    @Json(name = "status")
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "currentPage")
        val currentPage: Int,
        @Json(name = "data")
        val `data`: List<Data>,
        @Json(name = "nextPage")
        val nextPage: Boolean,
        @Json(name = "totalItems")
        val totalItems: Int,
        @Json(name = "totalPages")
        val totalPages: Int
    ) {
        @JsonClass(generateAdapter = true)
        data class Data(
            @Json(name = "content")
            val content: String,
            @Json(name = "createdAt")
            val createdAt: String,
            @Json(name = "deletedAt")
            val deletedAt: Any,
            @Json(name = "id")
            val id: Int,
            @Json(name = "image")
            val image: String,
            @Json(name = "title")
            val title: String,
            @Json(name = "updatedAt")
            val updatedAt: String,
            @Json(name = "user_id")
            val userId: Int
        )
    }
}
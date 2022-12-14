package org.sopt.sample.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ResponseFollowerDTO(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page : Int,
    @SerialName("total")
    val total : Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<Person>,
    @SerialName("support")
    val support: Support
)

@kotlinx.serialization.Serializable
data class Support(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String
)

@Serializable
data class Person(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val first_name: String,
    @SerialName("last_name")
    val last_name: String,
    // 이미지
    @SerialName("avatar")
    val avatar: String
)
)

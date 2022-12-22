package org.sopt.sample.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseLoginDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: User
){
    @kotlinx.serialization.Serializable
    data class User(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profileImage")
        val profileImage: String?,
        @SerialName("bio")
        val bio: String?,
        @SerialName("email")
        val email: String,
        @SerialName("password")
        val password: String
    )
}

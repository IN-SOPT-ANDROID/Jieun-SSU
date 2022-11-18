package org.sopt.sample.remote

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseSignupDTO(
    @SerialName("message")
    val message: String,
    @SerialName("newUser")
    val newUser: NewUser,
    @SerialName("status")
    val status: Int
) {
    @kotlinx.serialization.Serializable
    data class NewUser(
        @SerialName("bio")
        val bio: String?,
        @SerialName("email")
        val email: String,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("password")
        val password: String,
        @SerialName("profileImage")
        val profileImage: String?
    )
}

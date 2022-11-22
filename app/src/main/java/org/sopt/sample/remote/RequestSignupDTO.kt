package org.sopt.sample.remote

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RequestSignupDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String
)

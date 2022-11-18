package org.sopt.sample.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class RequestLoginDTO(
    @SerialName("email")
    val email: String,
    // @SerialName("name")
    // val name: String,
    @SerialName("password")
    val password: String
)
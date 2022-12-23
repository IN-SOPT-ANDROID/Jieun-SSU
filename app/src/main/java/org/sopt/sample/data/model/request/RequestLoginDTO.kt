package org.sopt.sample.data.model.request

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RequestLoginDTO(
    @SerialName("email")
    val email: String,
    // @SerialName("name")
    // val name: String,
    @SerialName("password")
    val password: String
)
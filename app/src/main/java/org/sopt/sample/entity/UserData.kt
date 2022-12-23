package org.sopt.sample.entity
import kotlinx.serialization.SerialName

data class UserData(
    @SerialName("avatar")
    val image: Int,
    @SerialName("first-name")
    val name: String,
    val author: String,
    @SerialName("email")
    val email: String
)
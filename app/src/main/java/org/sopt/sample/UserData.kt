package org.sopt.sample
import kotlinx.serialization.SerialName

data class UserData(
    @SerialName("avatar")
    val image:String,
    @SerialName("first-name")
    val name:String,
    val author:String,
    @SerialName("email")
    val email:String
)
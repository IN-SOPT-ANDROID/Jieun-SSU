package org.sopt.sample.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUploadDTO(
    val statusCode: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    @Serializable
    data class Data(
        val id: Int,
        val image: String,
        val title: String,
        val singer: String
    )
}

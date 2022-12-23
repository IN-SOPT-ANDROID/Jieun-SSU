package org.sopt.sample.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFetchDto(
    val statusCode: Int,
    val success: Boolean,
    val message: String,
    val data: List<ResponseMusicDto>?,

    )

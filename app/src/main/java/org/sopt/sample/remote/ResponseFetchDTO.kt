package org.sopt.sample.remote

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFetchDTO(
    val statusCode: Int,
    val success: Boolean,
    val message: String,
    val data: List<ResponseMusicDTO>?,

    )

package org.sopt.sample.remote

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMusicDTO(
    val data: List<Data>
) {
    @Serializable
    data class Data(
        val id: Int,
        val image: String,
        val singer: String,
        val title: String
    )
}

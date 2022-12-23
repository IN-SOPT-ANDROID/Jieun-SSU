package org.sopt.sample.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.model.response.ResponseFetchDto
import org.sopt.sample.data.model.response.ResponseUploadDto
import retrofit2.http.*

interface MusicUploadService {
    @GET("music/list")
    suspend fun fetchMusicList(): ResponseFetchDto

    @Multipart
    @POST("music")
    suspend fun uploadMusic(
        @PartMap request: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part?
    ): ResponseUploadDto
}
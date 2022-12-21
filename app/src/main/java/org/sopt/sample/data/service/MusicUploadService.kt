package org.sopt.sample.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.model.response.ResponseFetchDTO
import org.sopt.sample.data.model.response.ResponseUploadDTO
import retrofit2.http.*

interface MusicUploadService {
    @GET("music/list")
    suspend fun fetchMusicList(): ResponseFetchDTO

    @Multipart
    @POST("music")
    suspend fun uploadMusic(
        @PartMap request: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part?
    ): ResponseUploadDTO
}
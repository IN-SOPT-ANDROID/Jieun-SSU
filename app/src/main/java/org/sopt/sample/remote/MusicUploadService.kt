package org.sopt.sample.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
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
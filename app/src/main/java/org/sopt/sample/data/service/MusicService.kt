package org.sopt.sample.data.service

import org.sopt.sample.data.model.response.ResponseMusicDto
import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("music/list")
    fun getData(): Call<ResponseMusicDto>
}
package org.sopt.sample.remote

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("music/list")
    fun getData(): Call<ResponseMusicDTO>
}
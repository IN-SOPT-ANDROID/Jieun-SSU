package org.sopt.sample.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users?page=2")
    fun getData(@Query("page") page: Int): Call<ResponseFollowerDTO>
}
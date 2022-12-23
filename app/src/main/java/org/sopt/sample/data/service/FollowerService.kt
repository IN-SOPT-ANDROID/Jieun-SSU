package org.sopt.sample.data.service

import org.sopt.sample.data.model.response.ResponseFollowerDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    fun getData(@Query("page") page: Int): Call<ResponseFollowerDTO>
}
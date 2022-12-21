package org.sopt.sample.data.service

import okhttp3.MultipartBody
import org.sopt.sample.data.model.request.RequestLoginDTO
import org.sopt.sample.data.model.request.RequestSignupDTO
import org.sopt.sample.data.model.response.ResponseLoginDTO
import org.sopt.sample.data.model.response.ResponseSignupDTO
import retrofit2.Call
import retrofit2.http.*

// 로그인, 회원가입
interface AuthService {
    @POST("api/user/signin")
    fun login(@Body request: RequestLoginDTO): Call<ResponseLoginDTO>
    @POST("api/user/signup")
    fun signup(@Body request: RequestSignupDTO): Call<ResponseSignupDTO>
    @Multipart
    @POST("api/user/{userId}/image")
    fun uploadProfileImage(
        @Path("userId") userId: Int,
        @Part("image") image: MultipartBody.Part
    ): Call<Unit>
}
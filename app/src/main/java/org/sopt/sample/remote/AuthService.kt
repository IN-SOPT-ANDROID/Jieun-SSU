package org.sopt.sample.remote

import okhttp3.MultipartBody
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
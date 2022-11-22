package org.sopt.sample.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
// 로그인, 회원가입
interface AuthService {
    @POST("api/user/signin")
    fun login(@Body request: RequestLoginDTO): Call<ResponseLoginDTO>
    @POST("api/user/signup")
    fun signup(@Body request: RequestSignupDTO): Call<ResponseSignupDTO>
}
package org.sopt.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.remote.AuthService
import org.sopt.sample.remote.RequestLoginDTO
import org.sopt.sample.remote.ResponseLoginDTO
import org.sopt.sample.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val _loginResult: MutableLiveData<ResponseLoginDTO> = MutableLiveData()
    val loginResult: LiveData<ResponseLoginDTO> = _loginResult
    // 서버통신 값이 오면 loginResult를 선언한다.
    private val loginService = ServicePool.AuthService

    private val _successLogin = MutableLiveData<Boolean>()
    val successLogin: LiveData<Boolean> = _successLogin
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun login(email: String, password: String){
        loginService.login(
            RequestLoginDTO(email, password)
        ).enqueue(object: Callback<ResponseLoginDTO> {
            override fun onResponse(
                call: Call<ResponseLoginDTO>,
                response: Response<ResponseLoginDTO>
            ) {
                if (response.isSuccessful) {
                    _successLogin.value = true
                    _loginResult.value = response.body()
                } else { // 서버랑 연결은 되었으나 여러 문제가 발생 (400, 500 등)
                    _successLogin.value = false
                    _errorMessage.value = response.message()
                }
            }
                override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                    _successLogin.value = false
                    _errorMessage.value = t.message
                }
            }
        )
    }
}

package org.sopt.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.sample.remote.RequestSignupDTO
import org.sopt.sample.remote.ResponseSignupDTO
import org.sopt.sample.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    val Emailpattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
    val Pwpattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"

    // backing property: 왜 이런식으로 변수를 만드나? 외부 뷰에서 수정할 필요가 없는 변수
    private val _signupResult: MutableLiveData<ResponseSignupDTO> = MutableLiveData()
    val signupResult: LiveData<ResponseSignupDTO> = _signupResult
    private val signupService = ServicePool.AuthService

    private val _successSignup = MutableLiveData<Boolean>()
    val successSignup: LiveData<Boolean> = _successSignup
    private val _serverError = MutableLiveData<String>()
    val serverError: LiveData<String> = _serverError

    val inputEmail = MutableLiveData<String>().apply { value = "" }
    val inputPw = MutableLiveData<String>().apply { value = "" }
    val inputId = MutableLiveData<String>().apply { value = "" }

    val inputEmailcheck: LiveData<Boolean> = Transformations.map(inputEmail) { email ->
        validEmailcheck(email)
    } // map 이용

    val inputPwcheck: LiveData<Boolean> = Transformations.map(inputPw) { pw ->
        validPwcheck(pw)
    }

    fun signup(email: String, password: String, id: String) {
        signupService.signup(
            RequestSignupDTO(email, password, id)
        ).enqueue(object : Callback<ResponseSignupDTO> {
            override fun onResponse(
                call: Call<ResponseSignupDTO>,
                response: Response<ResponseSignupDTO>
            ) {
                if (response.isSuccessful) {
                    _signupResult.value = response.body()
                    _successSignup.value = true
                } else { // 서버 에러
                    _serverError.value = response.message()
                    _successSignup.value = false
                }
            }

            override fun onFailure(call: Call<ResponseSignupDTO>, t: Throwable) {
                // 서버통신 자체가 실패
                _serverError.value = t.message
                _successSignup.value = false
            }
        })
    }
    private fun validEmailcheck(Email: String): Boolean {
        val pattern = Pattern.compile(Emailpattern)
        return pattern.matcher(Email).find()
    }

    private fun validPwcheck(Pw: String): Boolean { ]
        val pattern = Pattern.compile(Pwpattern)
        return pattern.matcher(Pw).find()
    }
}
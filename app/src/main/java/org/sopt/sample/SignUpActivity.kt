package org.sopt.sample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.remote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//회원가입
//ID(6-10), password(8-12), mbti(조건없음) 가입 번튼 누른 후 조건에 맞을시
//회원가입 완료 스낵바를 로그인 화면에 띄우기
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSuccess.isEnabled = false
        checkInputSetting()
        ButtonClick()
    }

    private fun InputisNotEmpty() {
        with(binding) {
            if (etSignuppassword.text.toString().isNotBlank() && etSignupemail.text.toString()
                    .isNotBlank() && etSignupname.text.toString().isNotBlank()
            ) {
                //true 반환
                true
            }
            //false 반환
            false
        }
    }

    private fun checkInputSetting() {
            if (binding.etSignupemail.text.isEmpty() || binding.etSignuppassword.text.length !in 8..12 || binding.etSignupname.text.isEmpty()) {
                binding.btnSuccess.isEnabled = false
                return
            }
            binding.btnSuccess.isEnabled = true
    }

    private fun ButtonClick() {
        binding.btnSuccess.setOnClickListener() {
            /*
            if ((binding.etSignupid.text.length >= 6) && (binding.etSignupid.text.length <= 10)&&(binding.etSignuppassword.text.length >= 8) && (binding.etSignuppassword.text.length <= 12)) {
                    val intent = Intent(this, SignInActivity::class.java)  //val은 변하지 않는 값, var은 변하는 값
                // putExtra는 함수이며 값을 보냄.
                // 값을 보낸다기 보다는 intent로 담는다.
                    intent.putExtra("userid", binding.etSignupid.text.toString())
                    intent.putExtra("userpassword", binding.etSignuppassword.text.toString())
                    intent.putExtra("usermbti", binding.etSignupemail.text.toString())
                    setResult(RESULT_OK, intent)  //string 보냄
                    finish()  //아예 화면 나가기, 이거 안하면 계속 스택에 이 화면이 쌓여있음

            } else {
                Snackbar.make(binding.root, "다시 입력하세요", Snackbar.LENGTH_SHORT).show()
            }
            */
            AuthService.signup(
                RequestSignupDTO(
                    binding.etSignupemail.text.toString(),
                    binding.etSignuppassword.text.toString(),
                    binding.etSignupname.text.toString()
                )
            ).enqueue(object : Callback<ResponseSignupDTO> {
                override fun onResponse(
                    call: Call<ResponseSignupDTO>, response: Response<ResponseSignupDTO>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@SignUpActivity, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
                        val Intent = Intent(this@SignUpActivity, SignUpActivity::class.java)
                    } else {
                        // 이미 있는 계정이기에 회원가입 실패
                        Toast.makeText(this@SignUpActivity, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDTO>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "서버 통신에 오류가 발생하였습니다", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}


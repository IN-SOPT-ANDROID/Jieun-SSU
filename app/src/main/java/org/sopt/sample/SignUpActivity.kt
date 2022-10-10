package org.sopt.sample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.databinding.ActivitySignUpBinding

//회원가입
//ID(6-10), password(8-12), mbti(조건없음) 가입 번튼 누른 후 조건에 맞을시
//회원가입 완료 스낵바를 로그인 화면에 띄우기
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuccess.setOnClickListener() {
            if ((binding.etSignupid.text.length >= 6) && (binding.etSignupid.text.length <= 10)&&(binding.etSignuppassword.text.length >= 8) && (binding.etSignuppassword.text.length <= 12)) {
                    val intent =
                        Intent(this, SignInActivity::class.java)  //val은 변하지 않는 값, var은 변하는 값
                // putExtra는 함수이며 값을 보냄.
                // 값을 보낸다기 보다는 intent로 담는다.
                    intent.putExtra("userid", binding.etSignupid.text.toString())
                    intent.putExtra("userpassword", binding.etSignuppassword.text.toString())
                    intent.putExtra("usermbti", binding.etSignupmbti.text.toString())
                    setResult(RESULT_OK, intent)  //string 보냄
                    finish()  //아예 화면 나가기, 이거 안하면 계속 스택에 이 화면이 쌓여있음

            } else {
                Snackbar.make(binding.root, "다시 입력하세요", Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}

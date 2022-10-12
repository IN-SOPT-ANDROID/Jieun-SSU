package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.MainActivity

//로그인 페이지
class SignInActivity : AppCompatActivity() {  //여기서 class는 한 화면이라고 생각하면 된다.
    private lateinit var binding: ActivitySignInBinding
    private lateinit var id: String
    private var password: String? = null
    private var mbti: String? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)  //binding 부분 순서 주의하기
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    Snackbar.make(binding.root, "로그인을 성공했습니다", Snackbar.LENGTH_SHORT).show()
                    id = result.data?.getStringExtra("userid") ?: ""
                    password = result.data?.getStringExtra("userpassword") ?: ""
                    mbti = result.data?.getStringExtra("usermbti") ?: ""
                }
            }
        clickloginbtn()
        clicksignupbtn()
    }


    //? -이것은 엘비스 연산자라고 함, 엘비스 연산자는 앞값이 null 일때 뒷값으로 초기화해주는 작업을 해준다. null 값이 가능하다 (코틀린은 nullable이다)
    //SignUpActivity가 call back
    //로그인 성공
    private fun clickloginbtn() {  //함수같은 것도 다 class에 집어 넣기
        binding.btnLogin.setOnClickListener {

            if (binding.etSigninid.text.toString() == id) {
                if (binding.etSigninpassword.text.toString() != password) {
                    Snackbar.make(binding.root, "패스워드가 잘못 됐습니다.", Snackbar.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("id",id)
                    intent.putExtra("mbti", mbti)
                    startActivity(intent)
                    finish()
                }
            } else {
                Snackbar.make(binding.root, "아이디가 잘못 됐습니다.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    //회원가입 버튼
    private fun clicksignupbtn() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)  //signup에서 콜백을 받기 위 생성+ signup 페이지로 넘어가게 해줌
            binding.etSigninid.text = null  //sign up 누를 때마다 초기화 시키기
            binding.etSigninpassword.text = null
        }
    }
}



package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.remote.RequestLoginDTO
import org.sopt.sample.remote.ResponseLoginDTO
import org.sopt.sample.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 로그인 페이지
class SignInActivity : AppCompatActivity() { // 여기서 class는 한 화면이라고 생각하면 된다.
    private lateinit var binding: ActivitySignInBinding
    private var id: String? = null
    private var password: String? = null
    private var name: String? = null
    private val authService = ServicePool.AuthService
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root) // binding 부분 순서 주의하기
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    Snackbar.make(binding.root, "로그인을 성공했습니다", Snackbar.LENGTH_SHORT).show()
                   // id = result.data?.getStringExtra("userid") ?: ""
                   // password = result.data?.getStringExtra("userpassword") ?: ""
                   // name = result.data?.getStringExtra("username") ?: ""
                }
            }
        clickloginbtn()
        clicksignupbtn()
    }

    // ? -이것은 엘비스 연산자라고 함, 엘비스 연산자는 앞값이 null 일때 뒷값으로 초기화해주는 작업을 해준다. null 값이 가능하다 (코틀린은 nullable이다)
    // SignUpActivity가 call back
    // 로그인 성공2
    private fun clickloginbtn() { // 함수같은 것도 다 class에 집어 넣기
        val homeIntent = Intent(this, HomeActivity::class.java)
        binding.btnLogin.setOnClickListener() {
            if(binding.etSigninemail.text.isEmpty()){
                Toast.makeText(this@SignInActivity,"이메일을 입력하지 않았습니다",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(binding.etSigninpassword.text.length !in 6..10){
                Toast.makeText(this@SignInActivity,"비밀번호가 조건에 맞지 않습니다",Toast.LENGTH_SHORT).show()
            }
            // 로그인 api 연결
            authService.login(
                RequestLoginDTO(
                    binding.etSigninemail.text.toString(),
                    binding.etSigninpassword.text.toString()
                )
            ).enqueue(object : Callback<ResponseLoginDTO> {
                override fun onResponse(
                    call: Call<ResponseLoginDTO>,
                    response: Response<ResponseLoginDTO>
                ) {
                    // 로그인 성공했을 시
                    if (response.isSuccessful) {
                        Toast.makeText(this@SignInActivity, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignInActivity,HomeActivity::class.java).apply {
                            putExtra("id", response.body()?.result?.id)
                        }
                        startActivity(homeIntent)
                        finish()
                    }
                    else if (response.code() == 404) {
                        Snackbar.make(binding.root, "404 error 발생", Snackbar.LENGTH_LONG).show()
                    }
                    else if (response.code() == 401) {
                        Snackbar.make(binding.root, "401 error 발생", Snackbar.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                    Toast.makeText(this@SignInActivity, "서버통신에 오류가 발생하였습니다", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    // 회원가입 버튼
    private fun clicksignupbtn() {
        binding.btnSignup.setOnClickListener {
//            if (intent.hasExtra("id") && intent.hasExtra("password")) {
//                Snackbar.make(binding.root, "회원가입이 완료되었습니다", Snackbar.LENGTH_SHORT).show()
//
//                id = intent.getStringExtra("id")
//                password = intent.getStringExtra("password")
//            }
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent) // signup에서 콜백을 받기 위 생성+ signup 페이지로 넘어가게 해줌
            binding.etSigninemail.text = null // sign up 누를 때마다 초기화 시키기
            binding.etSigninpassword.text = null
            startActivity(intent)
        }
    }
}

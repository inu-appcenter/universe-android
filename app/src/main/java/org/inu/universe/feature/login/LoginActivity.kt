package org.inu.universe.feature.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import okhttp3.ResponseBody
import org.inu.universe.R
import org.inu.universe.databinding.ActivityLoginBinding
import org.inu.universe.feature.main.MainActivity
import org.inu.universe.feature.signup.SignupActivity
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.LoginRequest
import org.inu.universe.model.retrofit.LoginService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val myViewModel: LoginViewModel by viewModels()

    /**
     * 액티비티 실행 순서상 LoginViewModel에서 ApplicationContext를 쓸 수 없음:
     * SplashActivity -> LoginActivity(context 사용) -> MainActivity(context 설정)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = myViewModel

        //        with(binding) {
        //            lifecycleOwner = this@LoginActivity
        //            viewModel = myViewModel
        //        }

        myViewModel.openMainActivityEvent.observe(this) {
            // 뷰모델에서 openMainActivityEvent 에 새로운 이벤트가 발생하면
            // 아래 코드를 실행하겠다!
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        myViewModel.openSignUpActivityEvent.observe(this) {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}
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
    private val viewModel: LoginViewModel by viewModels()
    private val TAG = "로그인"

    var loginService = RetrofitBuilder().build().create(LoginService::class.java)

    var token : String = null.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.loginBtn.setOnClickListener {
            login()
        }

        binding.loginSignupTv.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    fun login() {
        val inputEmail = binding.emailEt.text.toString() + "@inu.ac.kr"
        val inputPassword = binding.passwordEt.text.toString()

        val req = LoginRequest("inuappcenter@inu.ac.kr", "universe")
        //val req = LoginRequest(inputEmail, inputPassword)

        loginService.requestLogin(req).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful){
                    Store.jwt = "Bearer " + response.headers().get("accessToken")
                    if(Store.jwt != null) {
                        Log.d(TAG, response.code().toString())
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "실패", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, response.code().toString())
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e(TAG, "onFailure")
                t.printStackTrace()
                Toast.makeText(this@LoginActivity, "로그인 실패!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getToken() {
        loginService.getToken(token).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if(response.isSuccessful) {
                }
                else {
                    Log.e("실패, response code : ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }


}
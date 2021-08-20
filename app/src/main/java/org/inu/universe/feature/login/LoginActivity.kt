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
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    var retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-13-124-191-131.ap-northeast-2.compute.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var loginService = retrofit.create(LoginService::class.java)

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
        val textId = binding.emailEt.text.toString()
        val textPw = binding.passwordEt.text.toString()

        val req = LoginRequest("inuappcenter@inu.ac.kr", "universe") // LoginRequest(textId, textPw)

        loginService.requestLogin(req).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful){
                    Store.jwt = response.headers().get("accessToken")
                    if(Store.jwt != null)
                        Log.d("jwt 저장 완료", response.headers().toString())
                } else {
                    Toast.makeText(this@LoginActivity, "실패", Toast.LENGTH_SHORT).show()
                    Log.e("response code : ", response.code().toString())
                }

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
                Log.e("로그인 실패!", "")
                Toast.makeText(this@LoginActivity, "로그인 실패!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getToken() {
        loginService.getToken(token).enqueue(object  : Callback<ResponseBody> {
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
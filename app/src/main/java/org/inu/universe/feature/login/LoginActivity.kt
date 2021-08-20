package org.inu.universe.feature.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import okhttp3.ResponseBody
import okhttp3.internal.http2.Header
import org.inu.universe.R
import org.inu.universe.databinding.ActivityInitializingProfileBinding
import org.inu.universe.databinding.ActivityLoginBinding
import org.inu.universe.feature.initializing_profile.InitializingProfileActivity
import org.inu.universe.feature.initializing_profile.InitializingProfileViewModel
import org.inu.universe.feature.signup.SignupActivity
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.LoginResponse
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
        val json= JSONObject()
        json.put("address", "inuappcenter")
        json.put("password", "universe")

        loginService.requestLogin(json).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    Log.d("code : ", response.code().toString())
                    Log.d("로그인 성공은 개뿔 : ", response.headers().toString())
                    Log.d("로그인 성공은 개뿔 : ", response.headers().get("accessToken").toString())
                    //Store.jwt = response.headers().get("accessToken")
//                    val intent = Intent(this@LoginActivity, InitializingProfileActivity::class.java)
//                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "login failed", Toast.LENGTH_SHORT).show()
                    Log.e("실패, response code : ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.printStackTrace()
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
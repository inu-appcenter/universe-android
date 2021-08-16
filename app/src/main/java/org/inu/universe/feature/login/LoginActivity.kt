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
import org.inu.universe.databinding.ActivityInitializingProfileBinding
import org.inu.universe.databinding.ActivityLoginBinding
import org.inu.universe.feature.initializing_profile.InitializingProfileActivity
import org.inu.universe.feature.initializing_profile.InitializingProfileViewModel
import org.inu.universe.feature.signup.SignupActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    var retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-3-38-49-9.ap-northeast-2.compute.amazonaws.com:8080/")
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
        var textId = binding.emailEt.text.toString()
        var textPw = binding.passwordEt.text.toString()

        loginService.requestLogin(textId, textPw).enqueue(object : Callback<Unit> {

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) =
                if (response.isSuccessful()){
                    val accessToken : String? = response.headers().get("accessToken")
                    val refreshToken : String? = response.headers().get("refreshToken")
                    token = accessToken.toString()

                    val intent = Intent(this@LoginActivity, InitializingProfileActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LoginActivity, "login failed", Toast.LENGTH_SHORT).show()
                }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("DEBUG", t.message.toString())
                Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getToken() {
        loginService.getToken(token).enqueue(object  : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }


}
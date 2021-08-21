package org.inu.universe.feature.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivitySignupBinding
import org.inu.universe.databinding.ActivitySignupEmailCheckBinding
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.EmailAuthenticationRequest
import org.inu.universe.model.retrofit.EmailRequest
import org.inu.universe.model.retrofit.EmailService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class SignupEmailCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupEmailCheckBinding
    private val viewModel: SignupViewModel by viewModels()

    private var Timer_tv: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup_email_check)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        Timer_tv = binding.TimerTv

        Timer.start()

        binding.backBtn.setOnClickListener {
            finish()
        }

        val emailService = RetrofitBuilder()
            .build()
            .create(EmailService::class.java)

        emailService.requestAuthenticationCode(EmailRequest(Store.email!!))
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful) {
                        Log.d("인증 코드 전송", "완료" + response.code())
                    }
                    else {
                        Log.e("이메일 코드 전송 실패, code ", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("이메일 코드 전송 실패", "onFailure")
                }
            })

        val inputCode = binding.inputAuthenticationCode.text.toString()
        binding.emailCheckCheckBtn.setOnClickListener {
            emailService.requestAuthentication(EmailAuthenticationRequest(Store.email!!, inputCode))
                .enqueue(object: Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(response.isSuccessful) {
                            Log.d("이메일 인증", "성공" + response.code())
                            finish()
                        }
                        else {
                            Log.e("이메일 인증 실패, code ", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("인증 실패", "onFailure")
                    }
                })
        }
    }


    private var Timer = object : CountDownTimer(240000, 1000) {
        override  fun onTick(millisUntilFinished: Long) {

            Timer_tv?.text = getString(R.string.formatted_time,
            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60, TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60)
        }

        override fun onFinish() {
            finish()
        }
    }
}
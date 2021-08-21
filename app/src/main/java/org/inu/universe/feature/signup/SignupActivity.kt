package org.inu.universe.feature.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivitySignupBinding
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.EmailRequest
import org.inu.universe.model.retrofit.EmailService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.settingBtn.setOnClickListener {
            requestSignUp()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    fun requestSignUp() {

        var isGoToJoin = true

        val email = findViewById<EditText>(R.id.email_et).text.toString()
        val password = findViewById<EditText>(R.id.password_et).text.toString()
        val passwordCheck = findViewById<EditText>(R.id.passwordCheck_et).text.toString()

        if(email.isEmpty()){
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        if(password.isEmpty()){
            Toast.makeText(this, "password을 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        if(passwordCheck.isEmpty()){
            Toast.makeText(this, "password check을 입력해주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }
        if(!password.equals(passwordCheck)){
            Toast.makeText(this, "비밀번호를 똑같이 입력하세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }

        if(password.length < 6) {
            Toast.makeText(this, "6자리 이상으로 만들어주세요", Toast.LENGTH_LONG).show()
            isGoToJoin = false
        }

        if(isGoToJoin){
            // 이메일 저장
            val emailService = RetrofitBuilder()
                .build()
                .create(EmailService::class.java)

            val inputEmail = binding.emailEt.text.toString() + "@inu.ac.kr"
            Log.d("전송 이메일 : ", inputEmail)
            Store.email = inputEmail

            val intent = Intent(this, SignupEmailCheckActivity::class.java)
            startActivity(intent)

        }


    }
}
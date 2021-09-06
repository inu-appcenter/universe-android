package org.inu.universe.feature.login

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.inu.universe.global.MyApplication
import org.inu.universe.global.SingleLiveEvent
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.LoginRequest
import org.inu.universe.model.retrofit.LoginService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val TAG = "로그인"

    private val loginService = RetrofitBuilder().build().create(LoginService::class.java)

    val email = ObservableField("")
    val password = ObservableField("")

    val openMainActivityEvent = SingleLiveEvent<Unit>()
    val openSignUpActivityEvent = SingleLiveEvent<Unit>()

    fun login() {
        val inputEmail = email.get() + "@inu.ac.kr"
        val inputPassword = password.get()

        val req = LoginRequest(inputEmail, inputPassword)

        loginService.requestLogin(req).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Store.jwt = "Bearer " + response.headers()["accessToken"]
                    openMainActivityEvent.call()
                } else {
                    Toast.makeText(MyApplication.context, "실패", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, response.code().toString())
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e(TAG, "onFailure")
                t.printStackTrace()
                Toast.makeText(MyApplication.context, "로그인 실패!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun signUp() {
        // 이벤트 발생!
        openSignUpActivityEvent.call()
    }
}
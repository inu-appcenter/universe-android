package org.inu.universe.feature.initializing_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.GeolocationPermissions
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivityInitializingProfileBinding
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.ProfileRequest
import org.inu.universe.model.retrofit.ProfileResponse
import org.inu.universe.model.retrofit.ProfileService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class InitializingProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitializingProfileBinding
    private val viewModel: InitializingProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_initializing_profile)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.initializationFinish.setOnClickListener { postProfile() }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "초기 프로필을 먼저 설정해야 합니다.", Toast.LENGTH_SHORT).show()
    }

    fun postProfile() {
        val nickname = binding.initializationNicknameInput.text.toString()
        val age = Integer.parseInt(binding.initializationAgeInput.selectedItem.toString())
        val gender = if(binding.initializationMale.isChecked) "남성" else "여성"
        val college = binding.initializationCollegeInput.selectedItem.toString()
        val major = binding.initializationMajorInput.selectedItem.toString()

        Log.d("프로필 생성 인풋 : ", nickname)
        Log.d("프로필 생성 인풋 : ", age.toString())
        Log.d("프로필 생성 인풋 : ", gender)
        Log.d("프로필 생성 인풋 : ", college)
        Log.d("프로필 생성 인풋 : ", major)

        val requestBody = ProfileRequest(nickname, age, gender, college, major)

        val profileService = RetrofitBuilder().build().create(ProfileService::class.java)
        profileService.createProfile(Store.jwt!!, requestBody)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.d("프로필 생성", "성공!!")
                        finish()
                    }
                    else {
                        Log.e("프로필 생성", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.e("프로필 생성", "onFailure")
                    t.printStackTrace()
                }
            })
    }
}
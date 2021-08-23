package org.inu.universe.feature.my_profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.inu.universe.global.Profile
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.ProfileService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response

class MyProfileViewModel : ViewModel() {
    val nickname = MutableLiveData("")
    val age = MutableLiveData(0)
    val gender = MutableLiveData("")
    val major = MutableLiveData("")
    val region = MutableLiveData("인천")
    val height = MutableLiveData("175")
    val bodyType = MutableLiveData("마름")
    val hashtag = MutableLiveData("노래부르기")
    val mbti = MutableLiveData("INFJ")
    val isGraduated = MutableLiveData(false)
    val introduction = MutableLiveData("고기 잘 구워요~!")

    // 유저 정보 가져오기
    fun loadProfile() {
        val profileService = RetrofitBuilder().build().create(ProfileService::class.java)
        profileService.getProfile(Store.jwt!!, Store.profileId!!)
            .enqueue(object : retrofit2.Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if(response.isSuccessful) {
                        Store.profile = response.body()!!
                        Log.d("유저 로드", "성공")
                        Log.d("유저 로드", Store.profile.nickName)
                        Log.d("유저 로드", Store.profile.age.toString())
                        Log.d("유저 로드", Store.profile.major)
                        setProfile()
                    }
                    else {
                        Log.e("유저 로드", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.e("유저 로드", "onFailure")
                    t.printStackTrace()
                }
            })
    }

    // 유저 정보 출력하기
    fun setProfile() {
        // 기본 정보
        nickname.postValue(Store.profile.nickName)
        age.postValue(Store.profile.age)
        gender.postValue(
            if(Store.profile.gender == "남성") "남" else "여"
        )
        major.postValue(Store.profile.major)

        // more
        region.postValue(Store.profile.region)
        height.postValue(Store.profile.height)
        bodyType.postValue(Store.profile.bodyType)
        hashtag.postValue(
            Store.profile.hashTagList?.joinToString("   ", "", "")
        )
        mbti.postValue(Store.profile.mbti)
        introduction.postValue(Store.profile.introduction)
    }
}
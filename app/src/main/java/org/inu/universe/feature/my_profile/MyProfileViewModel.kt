package org.inu.universe.feature.my_profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProfileViewModel : ViewModel() {
    val region = MutableLiveData("인천")
    val tall = MutableLiveData("175")
    val bodyType = MutableLiveData("마름")
    val hashtag = MutableLiveData("노래부르기")
    val mbti = MutableLiveData("INFJ")
    val isGraduated = MutableLiveData(false)
    val introduction = MutableLiveData("고기 잘 구워요~!")
}
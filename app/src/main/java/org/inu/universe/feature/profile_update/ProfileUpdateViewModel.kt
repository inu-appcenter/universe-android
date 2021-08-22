package org.inu.universe.feature.profile_update

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.inu.universe.global.Profile
import org.inu.universe.global.Store

class ProfileUpdateViewModel : ViewModel() {
    val shouldFinish = MutableLiveData<Boolean>()

    val nickname = MutableLiveData("민초")
    val age = MutableLiveData(21)
    val gender = MutableLiveData("여자")
    val major = MutableLiveData("컴퓨터공학부")
    val region = MutableLiveData("인천")
    val height = MutableLiveData("165")
    val bodyType = MutableLiveData("마름")
    val mbti = MutableLiveData("INFJ")
    val introduction = MutableLiveData("민초파 모여랏~!")
    val hashTagList = MutableLiveData("#민초단 #수영")

    fun loadProfileInfo() {
        val profile = Store.profile

        nickname.postValue(profile.nickName)
        age.postValue(profile.age)
        gender.postValue(profile.gender)
        major.postValue(profile.major)
        region.postValue(profile.region)
        height.postValue(profile.height)
        bodyType.postValue(profile.bodyType)
        mbti.postValue(profile.mbti)
        introduction.postValue(profile.introduction)
        //hashTagList.postValue()
    }

    fun onUndoClick() {
        shouldFinish.postValue(true)
    }
}
package org.inu.universe.feature.profile_update

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.inu.universe.global.Profile
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.ProfileService
import org.inu.universe.model.retrofit.RetrofitBuilder

class ProfileUpdateViewModel : ViewModel() {
    val shouldFinish = MutableLiveData<Boolean>()
    val shouldOpenPhotoDialog = MutableLiveData<Boolean>()

    val nickname = MutableLiveData("민초")
    val introduction = MutableLiveData("민초파 모여랏~!")
    val hashTagList = MutableLiveData("#민초단 #수영")

    var profile: Profile? = null

    fun loadProfile() {
        profile = Store.profile

        nickname.postValue(profile?.nickName)
        introduction.postValue(profile?.introduction)
        hashTagList.postValue(profile?.hashTagList?.joinToString("   ", "", ""))
    }

    fun onUndoClick() {
        shouldFinish.postValue(true)
    }

    fun onPhotoClick() {
        shouldOpenPhotoDialog.postValue(true)
    }
}
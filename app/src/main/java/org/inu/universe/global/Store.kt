package org.inu.universe.global

import androidx.lifecycle.MutableLiveData

object Store {
    var jwt: String? = null
    var profile = Profile("나는 개발자다", 24, "남자", "정보기술대학", "컴퓨터공학부",
    null, null, null, null, null, null, null)
    var email: String? = null
    var profileId: String? = null
    val shouldMyProfileUpdate = MutableLiveData<Boolean>()
}
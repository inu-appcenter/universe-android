package org.inu.universe.global

import androidx.lifecycle.MutableLiveData

object Store {
    var jwt: String? = null
    var profile = Profile.mock()
    var email: String? = null
    var profileId: String? = null
    val shouldMyProfileUpdate = MutableLiveData<Boolean>()
}
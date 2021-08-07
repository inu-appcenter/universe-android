package org.inu.universe.feature.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val shouldOpenWeb: MutableLiveData<Intent?> = MutableLiveData<Intent?>();

    fun onMbtiClick() {
        val intent = Intent(Intent.ACTION_VIEW,
            Uri.parse("https://www.16personalities.com/ko/%EB%AC%B4%EB%A3%8C-%EC%84%B1%EA%B2%A9-%EC%9C%A0%ED%98%95-%EA%B2%80%EC%82%AC"))
        shouldOpenWeb.postValue(intent)
    }
}
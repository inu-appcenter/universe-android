package org.inu.universe.feature.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.inu.universe.feature.matching_option.MatchingOptionActivity
import org.inu.universe.global.MyApplication

class MainViewModel : ViewModel() {
    val shouldStartActivity = MutableLiveData<Intent?>()

    fun onMbtiClick() {
        val intent = Intent(Intent.ACTION_VIEW,
            Uri.parse("https://www.16personalities.com/ko/%EB%AC%B4%EB%A3%8C-%EC%84%B1%EA%B2%A9-%EC%9C%A0%ED%98%95-%EA%B2%80%EC%82%AC"))
        shouldStartActivity.postValue(intent)
    }

    fun onMatchingOptionClick() {
        val intent = Intent(MyApplication.context, MatchingOptionActivity::class.java)
        shouldStartActivity.postValue(intent)
    }
}
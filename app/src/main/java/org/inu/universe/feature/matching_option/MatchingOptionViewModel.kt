package org.inu.universe.feature.matching_option

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MatchingOptionViewModel : ViewModel() {
    val shouldFinish = MutableLiveData<Boolean>()

    fun onClickUndo() {
        shouldFinish.postValue(true)
    }
}
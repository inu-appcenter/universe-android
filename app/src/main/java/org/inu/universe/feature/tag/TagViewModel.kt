package org.inu.universe.feature.tag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TagViewModel : ViewModel() {
    val shouldFinish = MutableLiveData<Boolean>()

    fun onUndoClick() {
        shouldFinish.postValue(true)
    }
}
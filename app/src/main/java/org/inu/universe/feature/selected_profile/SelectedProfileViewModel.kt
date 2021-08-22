package org.inu.universe.feature.selected_profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectedProfileViewModel : ViewModel() {
    val shouldFinish = MutableLiveData<Boolean>()

    fun onUndoClick() {
        shouldFinish.postValue(true)
    }
}
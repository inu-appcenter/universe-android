package org.inu.universe.feature.chatting_room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChattingRoomViewModel : ViewModel() {
    val shouldOpenDrawer = MutableLiveData(false)

    fun onDrawerMenuClick() {
        shouldOpenDrawer.postValue(true)
    }
}
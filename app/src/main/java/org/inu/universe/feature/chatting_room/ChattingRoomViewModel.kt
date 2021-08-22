package org.inu.universe.feature.chatting_room

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.inu.universe.R
import org.inu.universe.global.MyApplication
import org.inu.universe.global.SingleLiveEvent

class ChattingRoomViewModel : ViewModel() {
    val shouldOpenDrawer = MutableLiveData(false)
    val shouldOpenExitDialog = MutableLiveData<Boolean>()
    val shouldOpenReportDialog = MutableLiveData<Boolean>()
    val shouldFinish = MutableLiveData<Boolean>()

    private val _eventOpenDialog = SingleLiveEvent<Any>()
    val eventOpenDialog: LiveData<Any>
        get() = _eventOpenDialog

    fun onDrawerMenuClick() {
        shouldOpenDrawer.postValue(true)
    }

    fun onExitClick() {
        shouldOpenExitDialog.postValue(true)
    }

    fun onReportClick() {
        shouldOpenReportDialog.postValue(true)
    }

    fun onUndoClick() {
        shouldFinish.postValue(true)
    }
}
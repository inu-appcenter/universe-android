package org.inu.universe.feature.profile_update

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import org.inu.universe.R
import java.lang.IllegalStateException
import kotlin.ClassCastException

class PhotoDialog : DialogFragment() {
    internal lateinit var listener: NotifyDialogListener

    interface NotifyDialogListener {
        fun openGallery(dialog: PhotoDialog)
        fun openCamera(dialog: PhotoDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setItems(R.array.dialog_photo, DialogInterface.OnClickListener { dialog, which ->
                when(which) {
                    0 -> listener.openGallery(this)
                    1 -> listener.openCamera(this)
                    else -> Log.e("dialog", "error!")
                }
            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NotifyDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() + " must implement NoticeDialgListener"))
        }
    }
}
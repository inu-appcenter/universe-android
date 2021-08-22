package org.inu.universe.feature.profile_update

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import org.inu.universe.R
import java.lang.IllegalStateException

class PhotoDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setItems(R.array.dialog_photo, DialogInterface.OnClickListener { dialog, which ->  })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
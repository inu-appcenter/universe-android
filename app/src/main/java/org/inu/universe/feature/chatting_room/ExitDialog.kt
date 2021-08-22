package org.inu.universe.feature.chatting_room

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import org.inu.universe.R
import java.lang.IllegalStateException

class ExitDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.exit_modal_title)
                .setMessage(R.string.exit_modal_description)
                .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialogInterface, id ->

                })
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, id ->

                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
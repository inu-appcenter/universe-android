package org.inu.universe.feature.chatting_room

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ChatDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
    }
}
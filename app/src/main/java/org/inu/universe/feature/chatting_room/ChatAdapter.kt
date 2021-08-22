package org.inu.universe.feature.chatting_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class ChatAdapter(val list: List<ChatVO>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_me, parent, false)

        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.message.text = list[position].message
        holder.timeStamp.text = list[position].timeStamp
    }

    override fun getItemCount(): Int = list.size

    inner class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.chat_me_mesaage)
        val timeStamp: TextView = view.findViewById(R.id.chat_me_time_stamp)
    }

    data class ChatVO(val message: String?, val isMyChat: Boolean, val timeStamp: String)
}
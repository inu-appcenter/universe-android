package org.inu.universe.feature.chatting_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class ChatAdapter(val list: List<ChatVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ChatVO.TYPE_MY_MESSAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_me, parent, false)
                return MyChatViewHolder(view)
            }
            ChatVO.TYPE_YOUR_MESSAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_you, parent, false)
                return YourChatViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_system, parent, false)
                return SystemChatViewHolder(view)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyChatViewHolder) {
            holder.message.text = list[position].message
            holder.timeStamp.text = list[position].timeStamp
        }
        if(holder is YourChatViewHolder) {
            holder.message.text = list[position].message
            holder.timeStamp.text = list[position].timeStamp
        }
        if(holder is SystemChatViewHolder) {
            holder.message.text = list[position].message
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    inner class MyChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.chat_me_mesaage)
        val timeStamp: TextView = view.findViewById(R.id.chat_me_time_stamp)
    }

    inner class YourChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.chat_you_message)
        val timeStamp: TextView = view.findViewById(R.id.chat_you_time_stamp)
    }

    inner class SystemChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.chat_system_message)
    }

    data class ChatVO(val message: String?, val type: Int, val timeStamp: String) {
        companion object {
            val TYPE_MY_MESSAGE = 0
            val TYPE_YOUR_MESSAGE = 1
            val TYPE_SYSTEM_MESSAGE = 2
        }
    }
}
package org.inu.universe.feature.chatting_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class ChatListAdapter(val context: Context, private val ChatList : List<ChatInfo>)
    : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = ChatList[position].name
        holder.message?.text = ChatList[position].message
        holder.time?.text = ChatList[position].time
        holder.photo?.setImageResource(ChatList[position].photo)

        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = ChatList.size



    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var photo: ImageView? = null
        var name: TextView? = null
        var message: TextView? = null
        var time: TextView? = null
        init {
            photo = view.findViewById(R.id.chatList_photo)
            name = view.findViewById(R.id.chatList_name)
            message = view.findViewById(R.id.chatList_message)
            time = view.findViewById(R.id.chatList_time)
        }
    }

    data class ChatInfo(val photo : Int,
                          val name : String,
                          val message : String,
                          val time : String)

}
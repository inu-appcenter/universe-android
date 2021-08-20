package org.inu.universe.feature.chatting_list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R
import org.inu.universe.feature.chatting_room.ChattingRoomActivity
import org.inu.universe.feature.like_list.PersonAdapter

class ChatListFragment : Fragment() {

    lateinit var Adapter : ChatListAdapter

    val ChatInfoList = arrayListOf<ChatListAdapter.ChatInfo>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat_list, container, false)

        ChatInfoList.add(
            ChatListAdapter.ChatInfo(R.drawable.easfa,"김예강", "혹시 민초 좋아세요?", "오후 3:42")
        )



        Adapter = context?.let { ChatListAdapter(it, ChatInfoList) }!!

        Adapter.setItemClickListener(object: ChatListAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(activity, ChattingRoomActivity::class.java)
                startActivity(intent)
            }
        })

        val likePerson = view.findViewById<RecyclerView>(R.id.ChatList_chatList)
        likePerson.adapter = Adapter


        return view
    }
}
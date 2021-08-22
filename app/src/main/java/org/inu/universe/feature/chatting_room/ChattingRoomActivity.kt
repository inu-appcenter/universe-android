package org.inu.universe.feature.chatting_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import org.inu.universe.R
import org.inu.universe.databinding.ActivityChattingRoomBinding

class ChattingRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChattingRoomBinding
    private val viewModel: ChattingRoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_room)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.shouldOpenDrawer.observe(this, Observer {
            val drawer = findViewById<DrawerLayout>(R.id.chat_drawer)
            if (it)
                drawer.openDrawer(Gravity.RIGHT)
            else
                drawer.closeDrawer(Gravity.RIGHT)
        })

        setDummyChatList()
    }

    private fun setDummyChatList() {
        val chatList = arrayListOf(
            ChatAdapter.ChatVO("제가 이 앱 만들었어요!!", ChatAdapter.ChatVO.TYPE_MY_MESSAGE, "오후 2:00"),
            ChatAdapter.ChatVO("근데요?", ChatAdapter.ChatVO.TYPE_YOUR_MESSAGE, "오후 2:00"),
            ChatAdapter.ChatVO("그냥 그렇다구요...", ChatAdapter.ChatVO.TYPE_MY_MESSAGE, "오후 2:00"),
            ChatAdapter.ChatVO("상대방이 채팅방을 나갔습니다.", ChatAdapter.ChatVO.TYPE_SYSTEM_MESSAGE, "오후 2:00")
        )
        binding.chatTalkList.adapter = ChatAdapter(chatList)
    }
}
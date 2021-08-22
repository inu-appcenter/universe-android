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


    }
}
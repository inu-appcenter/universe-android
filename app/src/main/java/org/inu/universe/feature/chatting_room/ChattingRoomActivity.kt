package org.inu.universe.feature.chatting_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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
    }
}
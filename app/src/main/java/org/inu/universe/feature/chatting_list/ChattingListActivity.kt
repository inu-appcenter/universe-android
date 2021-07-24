package org.inu.universe.feature.chatting_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivityChattingListBinding

class ChattingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChattingListBinding
    private val viewModel: ChattingListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
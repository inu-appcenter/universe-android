package org.inu.universe.feature.tag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivityTagBinding

class TagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTagBinding
    private val viewModel: TagViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
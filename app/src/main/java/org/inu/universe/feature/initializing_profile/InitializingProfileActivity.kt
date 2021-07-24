package org.inu.universe.feature.initializing_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivityInitializingProfileBinding

class InitializingProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitializingProfileBinding
    private val viewModel: InitializingProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_initializing_profile)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
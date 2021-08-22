package org.inu.universe.feature.initializing_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    override fun onBackPressed() {
        Toast.makeText(this, "초기 프로필을 먼저 설정해야 합니다.", Toast.LENGTH_SHORT).show()
    }
}
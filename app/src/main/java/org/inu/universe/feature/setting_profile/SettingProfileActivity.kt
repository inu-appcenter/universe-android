package org.inu.universe.feature.setting_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivitySettingProfileBinding

class SettingProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingProfileBinding
    private val viewModel: SettingProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting_profile)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
package org.inu.universe.feature.matching_option

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.inu.universe.R
import org.inu.universe.databinding.ActivityInitializingProfileBinding
import org.inu.universe.databinding.ActivityMatchingOptionBinding
import org.inu.universe.feature.initializing_profile.InitializingProfileViewModel

class MatchingOptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMatchingOptionBinding
    private val viewModel: MatchingOptionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_matching_option)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //나이범위
        binding.rangeSeekbarAge.setOnRangeSeekbarChangeListener{ minValue, maxValue ->
            binding.rangeAge.setText("$minValue~$maxValue")
        }

        viewModel.shouldFinish.observe(this, Observer {
            finish()
        })
    }
}
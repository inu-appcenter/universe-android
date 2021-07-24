package org.inu.universe.feature.like_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivityLikeListBinding

class LikeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLikeListBinding
    private val viewModel: LikeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_like_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
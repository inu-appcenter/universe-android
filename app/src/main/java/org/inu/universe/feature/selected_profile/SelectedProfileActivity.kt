package org.inu.universe.feature.selected_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.inu.universe.R
import org.inu.universe.databinding.ActivitySelectedProfileBinding

class SelectedProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedProfileBinding
    private val viewModel: SelectedProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_selected_profile)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setActivity()
    }

    fun setActivity() {
        viewModel.shouldFinish.observe(this, Observer {
            finish()
        })
    }
}
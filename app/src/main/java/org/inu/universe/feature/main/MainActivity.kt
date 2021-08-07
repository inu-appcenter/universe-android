package org.inu.universe.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R
import org.inu.universe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val profiles = findViewById<RecyclerView>(R.id.main_profiles)
        profiles.adapter = ProfilesAdapter(
            arrayListOf(MainProfileInfo("123"), MainProfileInfo("123"), MainProfileInfo("123"), MainProfileInfo("123"))
        )

        viewModel.shouldOpenWeb.observe(this, Observer {
            startActivity(it)
        })
    }
}
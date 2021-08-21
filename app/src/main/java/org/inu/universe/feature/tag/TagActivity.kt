package org.inu.universe.feature.tag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val tags = findViewById<RecyclerView>(R.id.tag_tags)
        tags.layoutManager = GridLayoutManager(this, 2)

        tags.adapter = TagAdapter(listOf("만화", "취준", "운동", "공부", "유튜브"))
    }
}
package org.inu.universe.feature.profile_update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivityProfileUpdateBinding
import org.inu.universe.feature.tag.TagActivity

class ProfileUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileUpdateBinding
    val viewModel: ProfileUpdateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_update)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //viewModel.loadProfileInfo()
        setSpinners()
        findViewById<TextView>(R.id.profile_update_hashtag_input).setOnClickListener {
            val intent = Intent(this, TagActivity::class.java)
            startActivity(intent)
        }
    }

    fun setSpinners() {
        // 나이
        val inputAge = findViewById<Spinner>(R.id.profile_update_age_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.age_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputAge.adapter = adapter
        }

        // 단과대
        val inputCollege = findViewById<Spinner>(R.id.profile_update_college_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.college_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputCollege.adapter = adapter
        }

        // 전공
        val inputMajor = findViewById<Spinner>(R.id.profile_update_major_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.major_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputMajor.adapter = adapter
        }

        // 지역
        val inputRegion = findViewById<Spinner>(R.id.profile_update_region_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.region_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputRegion.adapter = adapter
        }

        // 키 & 체형
        val inputBodyType = findViewById<Spinner>(R.id.profile_update_body_type_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.body_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputBodyType.adapter = adapter
        }

        // MBTI
        val inputMBTI = findViewById<Spinner>(R.id.profile_update_mbti_input)
        ArrayAdapter.createFromResource(
            this,
            R.array.mbti_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputMBTI.adapter = adapter
        }
    }
}
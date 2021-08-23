package org.inu.universe.feature.profile_update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.forEach
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        findViewById<TextView>(R.id.profile_update_hashtag_input).setOnClickListener {
            val intent = Intent(this, TagActivity::class.java)
            startActivity(intent)
        }

        subscribe()
        viewModel.loadProfile()
        setProfile()
    }

    fun subscribe() {
        viewModel.shouldFinish.observe(this, Observer {
            finish()
        })

        viewModel.shouldOpenPhotoDialog.observe(this, Observer {
            PhotoDialog().show(supportFragmentManager, "photo")
        })
    }

    private fun setProfile() {
        setSpinner(binding.profileUpdateAgeInput, viewModel.profile?.age.toString())
        setSpinner(binding.profileUpdateCollegeInput, viewModel.profile?.college!!)
        setSpinner(binding.profileUpdateMajorInput, viewModel.profile?.major!!)
        if(viewModel.profile?.region != null)
            setSpinner(binding.profileUpdateAgeInput, viewModel.profile?.region!!)
        if(viewModel.profile?.mbti != null)
            setSpinner(binding.profileUpdateAgeInput, viewModel.profile?.mbti!!)
    }

    private fun setSpinner(spinner: Spinner, value: String) {
        val len = spinner.adapter.count
        (0 until len).forEach {
            if(spinner.getItemAtPosition(it).toString() == value)
                spinner.setSelection(it)
        }
    }
}
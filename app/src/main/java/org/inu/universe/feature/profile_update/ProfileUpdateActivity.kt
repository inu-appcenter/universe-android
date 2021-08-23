package org.inu.universe.feature.profile_update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.forEach
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.inu.universe.R
import org.inu.universe.databinding.ActivityProfileUpdateBinding
import org.inu.universe.feature.tag.TagActivity
import java.lang.Exception

class ProfileUpdateActivity : AppCompatActivity(), PhotoDialog.NotifyDialogListener {
    lateinit var binding: ActivityProfileUpdateBinding
    val viewModel: ProfileUpdateViewModel by viewModels()
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_update)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        findViewById<TextView>(R.id.profile_update_hashtag_input).setOnClickListener {
            val intent = Intent(this, TagActivity::class.java)
            startActivity(intent)
        }

        subscribe()
        viewModel.loadProfile()
        setProfile()
        binding.profileUpdateCollegeInput.onItemSelectedListener = OnSelectedCollegeItem()

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    val uri = result.data?.data
                    Glide.with(this).load(uri).into(binding.profileUpdatePhoto)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    inner class OnSelectedCollegeItem : AdapterView.OnItemSelectedListener {
        private val colleges = arrayListOf(
            R.array.administration_array, R.array.engineering_array, R.array.global_array, R.array.no_college_array,
            R.array.urban_array, R.array.education_array, R.array.social_sciences_array, R.array.bioscience_array, R.array.art_sports_array,
            R.array.liberal_arts_array, R.array.nature_array, R.array.information_technology_array)

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            ArrayAdapter.createFromResource(
                this@ProfileUpdateActivity,
                colleges[position],
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.profileUpdateMajorInput.adapter = adapter
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
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

    override fun openGallery(dialog: PhotoDialog) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(intent)
    }

    override fun openCamera(dialog: PhotoDialog) {
        // TODO("Not yet implemented")
    }
}
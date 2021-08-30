package org.inu.universe.feature.profile_update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.inu.universe.R
import org.inu.universe.databinding.ActivityProfileUpdateBinding
import org.inu.universe.feature.login.LoginActivity
import org.inu.universe.feature.tag.TagActivity
import org.inu.universe.global.Profile
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.ProfileService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.lang.Exception
import javax.security.auth.callback.Callback

class ProfileUpdateActivity : AppCompatActivity(), PhotoDialog.NotifyDialogListener {
    lateinit var binding: ActivityProfileUpdateBinding
    val viewModel: ProfileUpdateViewModel by viewModels()
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_update)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.profileUpdateHashtagInput.setOnClickListener {
            val intent = Intent(this, TagActivity::class.java)
            startActivity(intent)
        }

        binding.profileUpdateOk.setOnClickListener {
            onFinishClick(binding.profileUpdateOk)
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
        if(viewModel.profile?.region != null)
            setSpinner(binding.profileUpdateRegionInput, viewModel.profile?.region!!)
        if(viewModel.profile?.mbti != null)
            setSpinner(binding.profileUpdateMbtiInput, viewModel.profile?.mbti!!)

        // 단과대에 따라 학과 목록이 나오는데, 위에서 단과대 설정하면 비동기로 selected item 이 실행되는 거 같은데, 그게 너무 느려서 제대로 적용이 안 됨.
        // 그래서 일부러 딜레이 넣은 거
        Handler(Looper.getMainLooper()).postDelayed({
            setSpinner(binding.profileUpdateMajorInput, viewModel.profile?.major!!)
        }, 500)
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

    fun onFinishClick(view: View) {
        val profileService = RetrofitBuilder().build().create(ProfileService::class.java)
        val inputProfile = getInputProfile()

        if(Store.jwt != null) {
            profileService.updateProfile(Store.jwt!!, getFormImage(null), getFormProfile(inputProfile))
                .enqueue(object : retrofit2.Callback<Profile> {
                    override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                        if(response.isSuccessful) {
                            Log.d("프로필 업데이트", response.code().toString())
                        }
                        else {
                            Log.e("프로필 업데이트", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<Profile>, t: Throwable) {
                        Log.e("프로필 업데이트", "onFailure")
                        t.printStackTrace()
                    }
                })

        }
    }

    private fun getInputProfile() : Profile {
        return Profile(
            binding.profileUpdateNicknameInput.text.toString(),
            Integer.parseInt(binding.profileUpdateAgeInput.selectedItem.toString()),
            if (binding.profileUpdateMale.isChecked) "남성" else "여성",
            binding.profileUpdateCollegeInput.selectedItem.toString(),
            binding.profileUpdateMajorInput.toString(),
            binding.profileUpdateRegionInput.selectedItem.toString(),
            0.toString(),
            binding.profileUpdateBodyTypeInput.selectedItem.toString(),
            binding.profileUpdateMbtiInput.selectedItem.toString(),
            binding.profileUpdateIntroductionInput.text.toString(),
            arrayListOf(),
            false
        )
    }

    private fun getFormImage(image: File?): MultipartBody.Part {
        val file = RequestBody.create(MultipartBody.FORM, "")
        val body = MultipartBody.Part.createFormData("image", "", file)
        return body
    }

    private fun getFormProfile(profile: Profile): MultipartBody.Part {
        val str = Gson().toJson(profile)
        Log.d("json parsing", str)

        return MultipartBody.Part.createFormData(
            "request",
            str
        )
    }
}
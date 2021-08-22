package org.inu.universe.feature.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R
import org.inu.universe.databinding.FragmentMainBinding
import org.inu.universe.feature.initializing_profile.InitializingProfileActivity
import org.inu.universe.global.MyApplication
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.AccountIds
import org.inu.universe.model.retrofit.AccountService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response

class MainFragment : Fragment() {
    private lateinit var fragmentMainBinding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val profiles = view.findViewById<RecyclerView>(R.id.main_profiles)
        profiles?.adapter = ProfilesAdapter(
            arrayListOf(ProfilesAdapter.MainProfileInfo(""), ProfilesAdapter.MainProfileInfo(""),
                ProfilesAdapter.MainProfileInfo(""), ProfilesAdapter.MainProfileInfo("")
            )
        )

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(profiles)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.shouldStartActivity.observe(this as LifecycleOwner, Observer {
            startActivity(it)
        })

        verifyAndStartActivity()
    }

    // 만약 초기 프로필 설정이 안 되어 있다면 설정 화면으로 넘어감
    fun verifyAndStartActivity() {
        val accountService = RetrofitBuilder().build().create(AccountService::class.java)
        if (Store.jwt != null) {
            accountService.requestIds(Store.jwt!!)
                .enqueue(object : retrofit2.Callback<AccountIds> {
                    override fun onResponse(
                        call: Call<AccountIds>,
                        response: Response<AccountIds>
                    ) {
                        if (response.isSuccessful) {
                            Store.profileId = response.body()?.profileId

                            if (Store.profileId.equals("empty")) {
                                val intent = Intent(context, InitializingProfileActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            Log.e("id 가져오기", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<AccountIds>, t: Throwable) {
                        Log.e("id 가져오기", "onFailure")
                        t.printStackTrace()
                    }
                })
        }
    }
}
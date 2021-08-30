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
import org.inu.universe.model.retrofit.*
import retrofit2.*

class MainFragment : Fragment() {
    private lateinit var fragmentMainBinding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var profiles: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        profiles = view.findViewById(R.id.main_profiles)
        profiles.adapter = ProfilesAdapter(arrayListOf())

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

        getRecommendation()
    }

    private fun getRecommendation() {
        val service = RetrofitBuilder().build().create(RecommendationService::class.java)
        service.getRecommendation(Store.jwt!!)
            .enqueue(object: Callback<List<RecommendationResponse>> {
                override fun onResponse(
                    call: Call<List<RecommendationResponse>>,
                    response: Response<List<RecommendationResponse>>
                ) {
                    if(response.isSuccessful) {
                        profiles.adapter = ProfilesAdapter(response.body()!!)

                        Log.d("오늘의 유니", "성공!!")
                    }
                    else {
                        Log.e("오늘의 유니", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<List<RecommendationResponse>>, t: Throwable) {
                    Log.e("오늘의 유니", "onFailure")
                    t.printStackTrace()
                }
            })
    }
}
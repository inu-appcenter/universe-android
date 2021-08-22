package org.inu.universe.feature.main

import android.animation.ObjectAnimator
import android.os.Bundle
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
import org.inu.universe.databinding.FragmentMyProfileBinding
import org.inu.universe.feature.my_profile.MyProfileViewModel

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
    }
}
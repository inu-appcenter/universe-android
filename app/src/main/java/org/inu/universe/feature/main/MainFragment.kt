package org.inu.universe.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import org.inu.universe.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val profiles = view.findViewById<RecyclerView>(R.id.main_profiles)
        profiles?.adapter = ProfilesAdapter(
            arrayListOf(MainProfileInfo(""), MainProfileInfo(""), MainProfileInfo(""), MainProfileInfo(""))
        )

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(profiles)

        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding.lifecycleOwner = this
        //binding.viewModel = viewModel

        return view
    }
}
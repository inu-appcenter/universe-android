package org.inu.universe.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val profiles = container?.findViewById<RecyclerView>(R.id.main_profiles)
        profiles?.adapter = ProfilesAdapter(
            arrayListOf(MainProfileInfo(""), MainProfileInfo(""), MainProfileInfo(""), MainProfileInfo(""))
        )


        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding.lifecycleOwner = this
        //binding.viewModel = viewModel

        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}
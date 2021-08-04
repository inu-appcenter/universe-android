package org.inu.universe.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class ProfilesAdapter(val profiles: List<MainProfileInfo>) : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_profile, parent, false)

        return ProfilesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = profiles.size

    inner class ProfilesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}

data class MainProfileInfo(val str: String)
package org.inu.universe.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R
import org.inu.universe.model.retrofit.RecommendationResponse

class ProfilesAdapter(private val profiles: List<RecommendationResponse>) : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_profile, parent, false)

        return ProfilesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        holder.profileInfo.text = "${profiles[position].nickName}/ ${profiles[position].age}, ${if(profiles[position].gender == "남성") '남' else '여'}"
        holder.major.text = profiles[position].major
    }

    override fun getItemCount(): Int = profiles.size

    inner class ProfilesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileInfo: TextView = view.findViewById(R.id.item_profile_info)
        val major: TextView = view.findViewById(R.id.item_profile_major)
    }
}


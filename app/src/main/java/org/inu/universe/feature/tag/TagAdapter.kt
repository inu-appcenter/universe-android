package org.inu.universe.feature.tag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class TagAdapter(val list: List<String>) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tag, parent, false)

        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.tag.text = list[position]
    }

    override fun getItemCount(): Int = list.size

    inner class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tag = view.findViewById<ToggleButton>(R.id.toggle_btn_tag)
    }
}
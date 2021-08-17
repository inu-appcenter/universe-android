package org.inu.universe.feature.like_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class PersonAdapter(private val PersonList : List<PersonInfo>)
    : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_likelist_person, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = PersonList[position].name
        holder.age?.text = PersonList[position].age
        holder.gender?.text = PersonList[position].gender
        holder.photo?.setImageResource(PersonList[position].photo)
    }

    override fun getItemCount(): Int = PersonList.size



    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var photo: ImageView? = null
        var name: TextView? = null
        var age: TextView? = null
        var gender: TextView? = null
        init {
            photo = view.findViewById(R.id.person_photo)
            name = view.findViewById(R.id.person_name)
            age = view.findViewById(R.id.person_age)
            gender = view.findViewById(R.id.person_gender)
        }
    }

    data class PersonInfo(val photo : Int,
                          val name : String,
                          val age : String,
                          val gender : String)

}
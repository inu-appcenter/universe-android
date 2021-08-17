package org.inu.universe.feature.like_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.inu.universe.R

class LikeListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_like_list, container, false)

        val PersonInfoList = arrayListOf<PersonAdapter.PersonInfo>(
            PersonAdapter.PersonInfo(R.drawable.easfa,"김예강", "21", "남")
        )

        val likePerson = view.findViewById<RecyclerView>(R.id.likeList_person)
        likePerson.adapter = PersonAdapter(PersonInfoList)

        return view
    }
}
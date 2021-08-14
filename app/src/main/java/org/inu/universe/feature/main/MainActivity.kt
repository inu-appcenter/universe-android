package org.inu.universe.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.inu.universe.R
import org.inu.universe.feature.my_profile.MyProfileFragment

class MainActivity : AppCompatActivity() {
    val tabNames = arrayListOf("홈", "하트", "채팅", "프로필")
    val tabIcons = arrayListOf(
        R.drawable.ic_home_outline,
        R.drawable.ic_favorite_outline,
        R.drawable.ic_chat_bubble_outline,
        R.drawable.ic_person_outline)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabs = findViewById<TabLayout>(R.id.main_tabs)
        val viewPager = findViewById<ViewPager2>(R.id.main_viewpager)

        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabs, viewPager) {
            tab, position -> tab.text = tabNames[position]
        }.attach()

        (0 until 4).forEach {
            tabs.getTabAt(it)?.setIcon(tabIcons[it])
        }
    }

    private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MainFragment()
                3 -> MyProfileFragment()
                else -> MainFragment()
            }
        }
    }
}
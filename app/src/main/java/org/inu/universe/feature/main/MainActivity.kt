package org.inu.universe.feature.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.inu.universe.R
import org.inu.universe.databinding.FragmentMainBinding
import org.inu.universe.feature.chatting_list.ChatListFragment
import org.inu.universe.feature.initializing_profile.InitializingProfileActivity
import org.inu.universe.feature.like_list.LikeListFragment
import org.inu.universe.feature.my_profile.MyProfileFragment
import org.inu.universe.global.MyApplication
import org.inu.universe.global.Profile
import org.inu.universe.global.Store
import org.inu.universe.model.retrofit.AccountIds
import org.inu.universe.model.retrofit.AccountService
import org.inu.universe.model.retrofit.ProfileService
import org.inu.universe.model.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    val tabNames = arrayListOf("홈", "하트", "채팅", "프로필")
    val tabOutlineIcons = arrayListOf(
        R.drawable.ic_home_outline,
        R.drawable.ic_favorite_outline,
        R.drawable.ic_chat_bubble_outline,
        R.drawable.ic_person_outline)
    val tabFilledIcons = arrayListOf(
        R.drawable.ic_tab_home_fill,
        R.drawable.ic_tab_favorite_fill,
        R.drawable.ic_tab_chat_bubble_fill,
        R.drawable.ic_tab_person_fill)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApplication.context = this
        setTab()
        verifyAndStartActivity()
    }

    fun setTab() {
        // tab 초기화
        val viewPager = findViewById<ViewPager2>(R.id.main_viewpager)
        viewPager.adapter = PagerAdapter(this)
        val tabs: TabLayout = findViewById(R.id.main_tabs)
        TabLayoutMediator(tabs, viewPager) {
                tab, position -> tab.text = tabNames[position]
        }.attach()
        tabs.getTabAt(0)?.setIcon(tabOutlineIcons[0])
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabs.getTabAt(tab!!.position)?.setIcon(tabFilledIcons[tab.position])
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tabs.getTabAt(tab!!.position)?.setIcon(tabOutlineIcons[tab.position])
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        tabs.getTabAt(0)?.setIcon(tabFilledIcons[0])
        (1 until 4).forEach {
            tabs.getTabAt(it)?.setIcon(tabOutlineIcons[it])
        }
    }

    private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MainFragment()
                1 -> LikeListFragment()
                2 -> ChatListFragment()
                3 -> MyProfileFragment()
                else -> MainFragment()
            }
        }
    }

    // 만약 초기 프로필 설정이 안 되어 있다면 설정 화면으로 넘어감
    private fun verifyAndStartActivity() {
        val accountService = RetrofitBuilder().build().create(AccountService::class.java)
        if (Store.jwt != null) {
            accountService.requestIds(Store.jwt!!)
                .enqueue(object : retrofit2.Callback<AccountIds> {
                    override fun onResponse(
                        call: Call<AccountIds>,
                        response: Response<AccountIds>
                    ) {
                        if (response.isSuccessful) {
                            Store.profileId = response.body()?.profileId

                            if (Store.profileId.equals("empty")) {
                                val intent = Intent(this@MainActivity, InitializingProfileActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            Log.e("id 가져오기", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<AccountIds>, t: Throwable) {
                        Log.e("id 가져오기", "onFailure")
                        t.printStackTrace()
                    }
                })
        }
    }
}
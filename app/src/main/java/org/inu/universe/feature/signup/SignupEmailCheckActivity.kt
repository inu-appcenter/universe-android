package org.inu.universe.feature.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.inu.universe.R
import org.inu.universe.databinding.ActivitySignupBinding
import org.inu.universe.databinding.ActivitySignupEmailCheckBinding
import java.util.concurrent.TimeUnit

class SignupEmailCheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupEmailCheckBinding
    private val viewModel: SignupViewModel by viewModels()




    private var Timer_tv: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup_email_check)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        Timer_tv = binding.TimerTv

        Timer.start()

        binding.backBtn.setOnClickListener {
            finish()
        }

    }


    private var Timer = object : CountDownTimer(240000, 1000) {
        override  fun onTick(millisUntilFinished: Long) {

            Timer_tv?.text = getString(R.string.formatted_time,
            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60, TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60)
        }

        override fun onFinish() {
            finish()
        }
    }
}
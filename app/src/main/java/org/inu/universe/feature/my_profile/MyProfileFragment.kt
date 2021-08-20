package org.inu.universe.feature.my_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import org.inu.universe.R
import org.inu.universe.databinding.ActivitySignupBinding
import org.inu.universe.databinding.FragmentMyProfileBinding
import org.inu.universe.feature.signup.SignupViewModel

class MyProfileFragment : Fragment() {
    private lateinit var fragmentMyProfileBinding: FragmentMyProfileBinding
    private val viewModel: MyProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMyProfileBinding.bind(view)
        fragmentMyProfileBinding = binding
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
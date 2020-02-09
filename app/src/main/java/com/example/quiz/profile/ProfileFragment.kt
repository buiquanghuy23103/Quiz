package com.example.quiz.profile


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.FragmentProfileBinding
import com.example.quiz.framework.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun getLayoutId() = R.layout.fragment_profile

    override fun initViewModel()
            = getViewModel{ ProfileViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userDetails.observe(viewLifecycleOwner, Observer {
            binding.user = it
        })
    }

}

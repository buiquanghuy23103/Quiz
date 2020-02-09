package com.example.quiz.profile


import com.example.quiz.R
import com.example.quiz.databinding.FragmentProfileBinding
import com.example.quiz.framework.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun getLayoutId() = R.layout.fragment_profile

    override fun initViewModel()
            = getViewModel{ ProfileViewModel() }



}

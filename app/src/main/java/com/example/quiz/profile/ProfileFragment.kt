package com.example.quiz.profile


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.R
import com.example.quiz.databinding.FragmentProfileBinding
import com.example.quiz.framework.BaseFragment
import com.example.quiz.utils.getAppInjector
import javax.inject.Inject

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayoutId() = R.layout.fragment_profile

    override fun initViewModel(): ProfileViewModel {
        getAppInjector().inject(this)
        return ViewModelProviders.of(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserProfile()
        setupAchievementRecyclerView()

    }

    private fun setupUserProfile() {
        binding.user = viewModel.userDetails
    }

    private fun setupAchievementRecyclerView() {
        val adapter = AchievementListAdapter(viewModel.userDetails?.uid)
        binding.achievements.adapter = adapter

        viewModel.achievements.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }

}

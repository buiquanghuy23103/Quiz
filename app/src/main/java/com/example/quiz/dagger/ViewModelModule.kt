package com.example.quiz.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.categorylist.CategoryListViewModel
import com.example.quiz.profile.ProfileViewModel
import com.example.quiz.quiz.QuizViewModel
import com.example.quiz.quizviewpager.QuizViewPagerViewModel
import com.example.quiz.splashScreen.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    internal abstract fun splashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CategoryListViewModel::class)
    internal abstract fun categoryListViewModel(viewModel: CategoryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuizViewPagerViewModel::class)
    internal abstract fun quizViewPagerViewModel(viewModel: QuizViewPagerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuizViewModel::class)
    internal abstract fun quizViewModel(viewModel: QuizViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel

}
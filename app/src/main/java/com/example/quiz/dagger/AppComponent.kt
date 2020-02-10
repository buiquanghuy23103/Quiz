package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.categorylist.CategoryListFragment
import com.example.quiz.profile.ProfileFragment
import com.example.quiz.quiz.QuizFragment
import com.example.quiz.quizviewpager.QuizViewPagerFragment
import com.example.quiz.splashScreen.SplashScreenActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DatabaseModule::class,
        FirebaseModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(activity: SplashScreenActivity)
    fun inject(fragment: CategoryListFragment)
    fun inject(fragment: QuizViewPagerFragment)
    fun inject(fragment: QuizFragment)
    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
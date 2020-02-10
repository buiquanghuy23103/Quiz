package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.categorylist.CategoryListFragment
import com.example.quiz.profile.ProfileFragment
import com.example.quiz.quiz.QuizFragment
import com.example.quiz.quizList.QuizListActivity
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
    fun inject(activity: QuizListActivity)
    fun inject(fragment: QuizFragment)
    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
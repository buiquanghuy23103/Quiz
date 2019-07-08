package com.example.quiz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quiz.R

abstract class BaseActivity : AppCompatActivity() {
    protected abstract fun fragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        var fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (fragment == null){
            fragment = fragment()
            supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment).commit()
        }
    }
}
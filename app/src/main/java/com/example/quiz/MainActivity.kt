package com.example.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
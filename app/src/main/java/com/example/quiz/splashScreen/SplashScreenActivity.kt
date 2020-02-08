package com.example.quiz.splashScreen

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.splashTimeout
import gr.net.maroulis.library.EasySplashScreen

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreenConf = EasySplashScreen(this)
            .withFullScreen()
            .withTargetActivity(MainActivity::class.java)
            .withSplashTimeOut(splashTimeout)
            .withBackgroundResource(R.color.colorPrimaryDark)
            .withHeaderText("Header")
            .withFooterText("Footer")
            .withBeforeLogoText("Before logo text")
            .withAfterLogoText("After logo text ")
            .withLogo(R.mipmap.ic_launcher_round)

        splashScreenConf.headerTextView.setTextColor(Color.WHITE)
        splashScreenConf.footerTextView.setTextColor(Color.WHITE)
        splashScreenConf.beforeLogoTextView.setTextColor(Color.WHITE)
        splashScreenConf.afterLogoTextView.setTextColor(Color.WHITE)

        val splashScreen = splashScreenConf.create()
        setContentView(splashScreen)

    }
}

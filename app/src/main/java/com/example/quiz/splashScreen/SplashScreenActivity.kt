package com.example.quiz.splashScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.getAppInjector
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val RC_SIGN_IN = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getAppInjector().inject(this)
        val viewModel = ViewModelProviders
            .of(this, viewModelFactory)[SplashScreenViewModel::class.java]

        viewModel.downloadAll()

        if (viewModel.isLoggedIn()) {
            startMainActivity()
        } else {
            // Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )

            val authMethodPickerLayout = AuthMethodPickerLayout.Builder(R.layout.activity_login)
                .setGoogleButtonId(R.id.googleSignInButton)
                .setEmailButtonId(R.id.emailSignInButton)
                .build()

            // Create and launch sign-in intent
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .setAlwaysShowSignInMethodScreen(true)
                    .setLogo(R.mipmap.ic_launcher_round)
                    .setAuthMethodPickerLayout(authMethodPickerLayout)
                    .setTheme(R.style.AppTheme)
                    .build(),
                RC_SIGN_IN)

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                startMainActivity()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                if (response != null) {
                    Timber.e(response.error)
                }
            }
        }
    }


    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

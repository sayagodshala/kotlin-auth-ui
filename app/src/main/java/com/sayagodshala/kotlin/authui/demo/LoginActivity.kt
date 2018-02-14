package com.sayagodshala.kotlin.authui.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sayagodshala.kotlin.authui.*


class LoginActivity : AppCompatActivity(), AuthUIFragmentListener {

    private var authUIFragment: AuthUIFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val authUISettings = AuthUISettings()
        authUISettings.isSocialPlatformRequired = true
        authUISettings.isAppLogoRequired = true
        authUISettings.isTermsRequired = true
        authUISettings.isSignupRequired = true
        authUISettings.isFacebookLoginRequired = true
        authUISettings.isGoogleLoginRequired = true
        authUISettings.isForgotPasswordRequired = true
        authUISettings.appLogo = R.mipmap.my_logo
        authUISettings.loginTitle = getString(R.string.login_title)
        authUISettings.signupTitle = (getString(R.string.signup_title))
        authUISettings.forgotPasswordTitle = (getString(R.string.forgot_password_title))
        authUISettings.loginTerms = (getString(R.string.loggin_terms))
        authUISettings.signupTerms = (getString(R.string.signup_terms))
        authUISettings.facebookLoginTitle = (getString(R.string.login_with_facebook))
        authUISettings.facebookSignupTitle = (getString(R.string.signup_with_facebook))
        authUISettings.googleLoginTitle = (getString(R.string.login_with_google))
        authUISettings.googleSignupTitle = (getString(R.string.signup_with_google))
        authUISettings.loginToggleTitle = (getString(R.string.have_an_account))
        authUISettings.signupToggleTitle = (getString(R.string.dont_have_account))
        authUISettings.defaultView = AuthUIView.LOGIN
        authUISettings.materialTheme = MaterialTheme.GREEN

        authUIFragment = AuthUIFragment.newInstanceWithDefaultSettings()
        AuthUIFragment.loadFragment(this, authUIFragment!!, R.id.frame)
    }

    override fun onLoginClicked(user: AuthUIUser) {
        when (user.loginType) {
            LoginType.EMAIL -> Log.d("onLoginClicked", "email : " + user.email + ", password : " + user.password)
            LoginType.MOBILE -> Log.d("onLoginClicked", "mobile : " + user.mobile + ", password : " + user.password)
            LoginType.EMAIL_OR_MOBILE -> Log.d("onLoginClicked", "email/mobile : " + user.emailOrMobile + ", password : " + user.password)
        }
    }

    override fun onSignupClicked(user: AuthUIUser) {
        Log.d("onSignupClicked", "name : " + user.name + ", email : " + user.email + ", mobile : " + user.mobile + ", password : " + user.password)
    }

    override fun onForgotPasswordClicked(user: AuthUIUser) {
        Log.d("onForgotPasswordClicked", "email : " + user.email)
        authUIFragment!!.recallLoginView()
    }

    override fun onSocialAccountClicked(socialAccount: SocialAccount, isRegistration: Boolean) {
        Log.d("onSocialAccountClicked", "socialAccount : " + socialAccount.name + ", isRegistration : " + isRegistration)
        when (socialAccount) {
            SocialAccount.FACEBOOK -> {
            }
            SocialAccount.GOOGLE -> {
            }
        }
    }

    override fun onFormValidationError(error: String) {
        Log.d("onFormValidationError", "error : " + error)
    }
}

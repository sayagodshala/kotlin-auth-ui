package com.sayagodshala.kotlin.authui

interface AuthUIFragmentListener {
    fun onLoginClicked(user: AuthUIUser)

    fun onSignupClicked(user: AuthUIUser)

    fun onForgotPasswordClicked(user: AuthUIUser)

    fun onSocialAccountClicked(socialAccount: SocialAccount, isRegistration: Boolean)

    fun onFormValidationError(error: String)
}
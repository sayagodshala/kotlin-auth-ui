package com.sayagodshala.kotlin.authui

enum class AuthUIView private constructor(val description: String) {
    LOGIN("login"),
    SIGNUP("signup"),
    FORGOT_PASSWORD("signup")
}

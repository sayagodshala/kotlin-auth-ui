package com.sayagodshala.kotlin.authui

import android.os.Parcel
import android.os.Parcelable

class AuthUISettings : Parcelable {

    var isSocialPlatformRequired = true
    var isAppLogoRequired = true
    var isTermsRequired = true
    var isSignupRequired = true
    var isFacebookLoginRequired = true
    var isGoogleLoginRequired = true
    var isForgotPasswordRequired = true
    var isHandleFormError = false
    var loginType = LoginType.EMAIL
    var defaultView = AuthUIView.LOGIN
    var materialTheme = MaterialTheme.BLUE_GREY
    var appLogo = R.mipmap.ic_launcher
    var bg: Int = 0
    var loginTitle = "Login using your registered details."
    var signupTitle = "Want to join? Signup in a seconds"
    var forgotPasswordTitle = "Put in your email id for password reset link"
    var nameHint = "Name"
    var emailHint = "Email"
    var mobileHint = "Mobile"
    var passwordHint = "Password"
    var loginTerms = "By Logging in I agree to the Terms of Use"
    var signupTerms = "By Signing up I agree to the Terms of Use"
    var facebookLoginTitle = "Login with Facebook"
    var facebookSignupTitle = "Signup with Facebook"
    var googleLoginTitle = "Login with Google"
    var googleSignupTitle = "Signup with Google"
    var loginToggleTitle = "Have an account? <b>LOGIN</b>"
    var signupToggleTitle = "Don't have an account? <b>SIGN UP</b>"


    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (this.isSocialPlatformRequired) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isAppLogoRequired) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isTermsRequired) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isSignupRequired) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isFacebookLoginRequired) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isGoogleLoginRequired) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isForgotPasswordRequired) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isHandleFormError) 1.toByte() else 0.toByte())
        dest.writeString(this.loginType.name)
        dest.writeString(this.defaultView.name)
        dest.writeString(this.materialTheme.name)
        dest.writeInt(this.appLogo)
        dest.writeInt(this.bg)
        dest.writeString(this.loginTitle)
        dest.writeString(this.signupTitle)
        dest.writeString(this.forgotPasswordTitle)
        dest.writeString(this.nameHint)
        dest.writeString(this.emailHint)
        dest.writeString(this.mobileHint)
        dest.writeString(this.passwordHint)
        dest.writeString(this.loginTerms)
        dest.writeString(this.signupTerms)
        dest.writeString(this.facebookLoginTitle)
        dest.writeString(this.facebookSignupTitle)
        dest.writeString(this.googleLoginTitle)
        dest.writeString(this.googleSignupTitle)
        dest.writeString(this.loginToggleTitle)
        dest.writeString(this.signupToggleTitle)
    }

    protected constructor(`in`: Parcel) {
        this.isSocialPlatformRequired = `in`.readByte().toInt() != 0
        this.isAppLogoRequired = `in`.readByte().toInt() != 0
        this.isTermsRequired = `in`.readByte().toInt() != 0
        this.isSignupRequired = `in`.readByte().toInt() != 0
        this.isFacebookLoginRequired = `in`.readByte().toInt() != 0
        this.isGoogleLoginRequired = `in`.readByte().toInt() != 0
        this.isForgotPasswordRequired = `in`.readByte().toInt() != 0
        this.isHandleFormError = `in`.readByte().toInt() != 0
        this.loginType = LoginType.valueOf(`in`.readString())
        this.defaultView = AuthUIView.valueOf(`in`.readString())
        this.materialTheme = MaterialTheme.valueOf(`in`.readString())
        this.appLogo = `in`.readInt()
        this.bg = `in`.readInt()
        this.loginTitle = `in`.readString()
        this.signupTitle = `in`.readString()
        this.forgotPasswordTitle = `in`.readString()
        this.nameHint = `in`.readString()
        this.emailHint = `in`.readString()
        this.mobileHint = `in`.readString()
        this.passwordHint = `in`.readString()
        this.loginTerms = `in`.readString()
        this.signupTerms = `in`.readString()
        this.facebookLoginTitle = `in`.readString()
        this.facebookSignupTitle = `in`.readString()
        this.googleLoginTitle = `in`.readString()
        this.googleSignupTitle = `in`.readString()
        this.loginToggleTitle = `in`.readString()
        this.signupToggleTitle = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<AuthUISettings> = object : Parcelable.Creator<AuthUISettings> {
            override fun createFromParcel(source: Parcel): AuthUISettings {
                return AuthUISettings(source)
            }

            override fun newArray(size: Int): Array<AuthUISettings?> {
                return arrayOfNulls(size)
            }
        }
    }
}

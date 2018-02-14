package com.sayagodshala.kotlin.authui

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.google.gson.Gson
import com.sayagodshala.kotlin.authui.R


class AuthUIFragment : Fragment(), View.OnClickListener {

    private var bundle: Bundle? = null
    private var mListener: AuthUIFragmentListener? = null
    internal var bg: ImageView? = null
    internal var appLogo: ImageView? = null
    internal var title: TextView? = null
    internal var layoutName: TextInputLayout? = null
    internal var name: EditText? = null
    internal var layoutEmail: TextInputLayout? = null
    internal var email: EditText? = null
    internal var layoutMobile: TextInputLayout? = null
    internal var mobile: EditText? = null
    internal var layoutPassword: TextInputLayout? = null
    internal var password: EditText? = null
    internal var proceed: Button? = null
    internal var forgotPassword: TextView? = null
    internal var orCont: LinearLayout? = null
    internal var socials: LinearLayout? = null
    internal var belowCont: LinearLayout? = null
    internal var fpLink: LinearLayout? = null
    internal var fb: RelativeLayout? = null
    internal var google: RelativeLayout? = null
    internal var terms: TextView? = null
    internal var signinSignup: TextView? = null
    internal var facebookTv: TextView? = null
    internal var googleTv: TextView? = null
    internal var or: TextView? = null
    internal var fpLogin: TextView? = null
    internal var fpSignup: TextView? = null
    internal var socialDivider: View? = null
    internal var view: View? = null

    private var authUISettings: AuthUISettings? = null

    private val isSignInValid: Boolean
        get() {
            var validationMessage = ""
            val emailStr = email!!.text.toString().trim { it <= ' ' }
            val passwordStr = password!!.text.toString().trim { it <= ' ' }
            if (!isValidEmail(emailStr)) {
                email!!.requestFocus()
                validationMessage = "Please enter valid email"
            } else if (textIsEmpty(passwordStr)) {
                password!!.requestFocus()
                validationMessage = "Invalid Password"
            }

            checkErrorMessage(validationMessage)

            return validationMessage.length == 0
        }

    private val isSignInWithEmailOrMobileValid: Boolean
        get() {
            var validationMessage = ""
            val emailStr = email!!.text.toString().trim { it <= ' ' }
            val passwordStr = password!!.text.toString().trim { it <= ' ' }
            if (textIsEmpty(emailStr)) {
                email!!.requestFocus()
                validationMessage = "Please enter email/mobile"
            } else if (textIsEmpty(passwordStr)) {
                password!!.requestFocus()
                validationMessage = "Invalid Password"
            }

            checkErrorMessage(validationMessage)

            return validationMessage.length == 0
        }

    private val isSignInWithMobileAndPasswordValid: Boolean
        get() {
            var validationMessage = ""
            val mobileStr = mobile!!.text.toString().trim { it <= ' ' }
            val passwordStr = password!!.text.toString().trim { it <= ' ' }
            if (textIsEmpty(mobileStr)) {
                mobile!!.requestFocus()
                validationMessage = "Please enter valid mobile"
            } else if (mobileStr.length < 10) {
                validationMessage = "mobile number should be 10 digits long"
            } else if (textIsEmpty(passwordStr)) {
                password!!.requestFocus()
                validationMessage = "Invalid Password"
            }

            checkErrorMessage(validationMessage)

            return validationMessage.length == 0
        }

    private val isSignUpValid: Boolean
        get() {
            var validationMessage = ""
            val nameStr = name!!.text.toString().trim { it <= ' ' }
            val emailStr = email!!.text.toString().trim { it <= ' ' }
            val mobileStr = mobile!!.text.toString().trim { it <= ' ' }
            val passwordStr = password!!.text.toString().trim { it <= ' ' }
            if (textIsEmpty(nameStr)) {
                name!!.requestFocus()
                validationMessage = "Please enter name"
            } else if (!isValidEmail(emailStr)) {
                email!!.requestFocus()
                validationMessage = "Please enter valid email"
            } else if (textIsEmpty(mobileStr) && mobileStr.length < 10) {
                mobile!!.requestFocus()
                validationMessage = "Please enter mobile"
            } else if (textIsEmpty(passwordStr)) {
                mobile!!.requestFocus()
                validationMessage = "Please enter password"
            }

            if (validationMessage.length != 0)
                showSnackBar(validationMessage)

            return validationMessage.length == 0
        }

    private val isForgotPasswordValid: Boolean
        get() {
            var validationMessage = ""
            val emailStr = email!!.text.toString().trim { it <= ' ' }
            if (!isValidEmail(emailStr)) {
                email!!.requestFocus()
                validationMessage = "Please enter valid email"
            }

            if (validationMessage.length != 0)
                showSnackBar(validationMessage)

            return validationMessage.length == 0
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_auth, container, false)
        bundle = arguments
        if (bundle != null && bundle!!.containsKey(AUTHUI_SETTINGS)) {
            authUISettings = bundle!!.getParcelable<Parcelable>(AUTHUI_SETTINGS) as AuthUISettings?
            Log.d("AuthUISettings", Gson().toJson(authUISettings))
        } else {
            authUISettings = AuthUISettings()
            Log.d("Default AuthUISettings", Gson().toJson(authUISettings))
        }
        bindView(view!!)
        setClickListener()
        bindData()
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AuthUIFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement AuthUIFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(view: View) {
        if (view.id == R.id.proceed) {
            if (layoutName!!.visibility == View.VISIBLE) {
                if (isSignUpValid) {
                    if (mListener != null) {
                        val authUIUser = AuthUIUser()
                        authUIUser.name = name!!.text.toString()
                        authUIUser.email = email!!.text.toString()
                        authUIUser.mobile = mobile!!.text.toString()
                        authUIUser.password = password!!.text.toString()
                        mListener!!.onSignupClicked(authUIUser)
                    }
                }
            } else {
                if (proceed!!.text.toString().equals("login", ignoreCase = true)) {
                    val authUIUser = AuthUIUser()
                    authUIUser.loginType = authUISettings!!.loginType
                    when (authUISettings!!.loginType) {
                        LoginType.EMAIL -> if (isSignInValid) {
                            authUIUser.email = email!!.text.toString()
                            authUIUser.password = password!!.text.toString()
                            mListener!!.onLoginClicked(authUIUser)
                        }
                        LoginType.MOBILE -> if (isSignInWithMobileAndPasswordValid) {
                            authUIUser.mobile = mobile!!.text.toString()
                            authUIUser.password = password!!.text.toString()
                            mListener!!.onLoginClicked(authUIUser)
                        }
                        LoginType.EMAIL_OR_MOBILE -> if (isSignInWithEmailOrMobileValid) {
                            authUIUser.emailOrMobile = email!!.text.toString()
                            authUIUser.password = password!!.text.toString()
                            mListener!!.onLoginClicked(authUIUser)
                        }
                    }

                } else {
                    if (isForgotPasswordValid) {
                        if (mListener != null) {
                            mListener!!.onForgotPasswordClicked(AuthUIUser(email!!.text.toString()))
                        }
                    }
                }

            }

        } else if (view.id == R.id.fb) {
            if (mListener != null)
                mListener!!.onSocialAccountClicked(SocialAccount.FACEBOOK, layoutName!!.visibility == View.VISIBLE)
        } else if (view.id == R.id.google) {
            if (mListener != null)
                mListener!!.onSocialAccountClicked(SocialAccount.GOOGLE, layoutName!!.visibility == View.VISIBLE)
        } else if (view.id == R.id.signin_signup) {
            if (!authUISettings!!.isSignupRequired)
                return
            if (layoutName!!.visibility == View.VISIBLE) {
                setLoginView()
            } else {
                setSignupView()
            }
        } else if (view.id == R.id.forgot_password) {
            setForgotPasswordView()
        } else if (view.id == R.id.fp_login) {
            setLoginView()
        } else if (view.id == R.id.fp_signup) {
            setSignupView()
        }

    }

    private fun bindView(view: View) {
        bg = view.findViewById(R.id.bg)
        appLogo = view.findViewById(R.id.app_logo)
        title = view.findViewById(R.id.title)
        layoutName = view.findViewById(R.id.layout_name)
        name = view.findViewById(R.id.name)
        layoutEmail = view.findViewById(R.id.layout_email)
        email = view.findViewById(R.id.email)
        layoutMobile = view.findViewById(R.id.layout_mobile)
        mobile = view.findViewById(R.id.mobile)
        layoutPassword = view.findViewById(R.id.layout_password)
        password = view.findViewById(R.id.password)
        proceed = view.findViewById(R.id.proceed)
        orCont = view.findViewById(R.id.or_cont)
        forgotPassword = view.findViewById(R.id.forgot_password)
        socials = view.findViewById(R.id.socials)
        fb = view.findViewById(R.id.fb)
        google = view.findViewById(R.id.google)
        terms = view.findViewById(R.id.terms)
        signinSignup = view.findViewById(R.id.signin_signup)
        facebookTv = view.findViewById(R.id.facebook_tv)
        googleTv = view.findViewById(R.id.google_tv)
        or = view.findViewById(R.id.or)
        socialDivider = view.findViewById(R.id.social_divider)
        belowCont = view.findViewById(R.id.below_cont)
        fpLink = view.findViewById(R.id.fp_link)
        fpLogin = view.findViewById(R.id.fp_login)
        fpSignup = view.findViewById(R.id.fp_signup)
    }

    private fun setClickListener() {
        signinSignup!!.setOnClickListener(this)
        proceed!!.setOnClickListener(this)
        google!!.setOnClickListener(this)
        fb!!.setOnClickListener(this)
        forgotPassword!!.setOnClickListener(this)
        fpLogin!!.setOnClickListener(this)
        fpSignup!!.setOnClickListener(this)
    }

    fun recallLoginView() {
        bindData()
    }

    fun bindData() {
        email!!.setText("")
        if (authUISettings != null) {
            if (!authUISettings!!.isFacebookLoginRequired && !authUISettings!!.isGoogleLoginRequired) {
                authUISettings!!.isSocialPlatformRequired = false
            }

            if (!authUISettings!!.isSocialPlatformRequired) {
                socials!!.visibility = View.GONE
                orCont!!.visibility = View.GONE
            }

            if (!authUISettings!!.isFacebookLoginRequired) {
                socialDivider!!.visibility = View.GONE
                fb!!.visibility = View.GONE
            }

            if (!authUISettings!!.isGoogleLoginRequired) {
                socialDivider!!.visibility = View.GONE
                google!!.visibility = View.GONE
            }

            if (!authUISettings!!.isAppLogoRequired) {
                appLogo!!.visibility = View.VISIBLE
            } else {
                if (authUISettings!!.appLogo != 0) {
                    appLogo!!.setImageResource(authUISettings!!.appLogo)
                }
            }

            when (authUISettings!!.defaultView) {
                AuthUIView.LOGIN -> setLoginView()
                AuthUIView.SIGNUP -> setSignupView()
                else -> setForgotPasswordView()
            }
            applyTheme()
        }
    }

    private fun setLoginView() {
        layoutName!!.visibility = View.GONE
        layoutMobile!!.visibility = View.GONE
        fpLink!!.visibility = View.GONE
        layoutPassword!!.visibility = View.VISIBLE
        layoutEmail!!.visibility = View.VISIBLE
        if (!authUISettings!!.isForgotPasswordRequired) {
            forgotPassword!!.visibility = View.GONE
        } else {
            forgotPassword!!.visibility = View.VISIBLE
        }
        belowCont!!.visibility = View.VISIBLE
        title!!.text = Html.fromHtml(authUISettings!!.loginTitle)
        terms!!.text = Html.fromHtml(authUISettings!!.loginTerms)

        if (!textIsEmpty(authUISettings!!.emailHint))
            layoutEmail!!.hint = authUISettings!!.emailHint
        if (!textIsEmpty(authUISettings!!.passwordHint))
            layoutPassword!!.hint = authUISettings!!.passwordHint

        when (authUISettings!!.loginType) {
            LoginType.EMAIL -> {
            }
            LoginType.MOBILE -> {
                layoutEmail!!.visibility = View.GONE
                layoutMobile!!.visibility = View.VISIBLE
            }
            LoginType.EMAIL_OR_MOBILE -> layoutEmail!!.hint = "Email/Mobile"
        }

        if (authUISettings!!.isSocialPlatformRequired) {
            facebookTv!!.text = Html.fromHtml(authUISettings!!.facebookLoginTitle)
            googleTv!!.text = Html.fromHtml(authUISettings!!.googleLoginTitle)
        }

        if (authUISettings!!.isSignupRequired) {
            signinSignup!!.visibility = View.VISIBLE
            signinSignup!!.text = Html.fromHtml(authUISettings!!.signupToggleTitle)
        } else {
            signinSignup!!.visibility = View.GONE
        }

        proceed!!.text = getString(R.string.loggin)
    }

    private fun setSignupView() {
        layoutName!!.visibility = View.VISIBLE
        layoutMobile!!.visibility = View.VISIBLE
        layoutEmail!!.visibility = View.VISIBLE
        layoutPassword!!.visibility = View.VISIBLE
        fpLink!!.visibility = View.GONE
        forgotPassword!!.visibility = View.GONE
        title!!.text = authUISettings!!.signupTitle
        terms!!.text = authUISettings!!.signupTerms
        belowCont!!.visibility = View.VISIBLE

        if (!textIsEmpty(authUISettings!!.emailHint))
            layoutEmail!!.hint = authUISettings!!.emailHint
        if (!textIsEmpty(authUISettings!!.mobileHint))
            layoutMobile!!.hint = authUISettings!!.mobileHint
        if (!textIsEmpty(authUISettings!!.passwordHint))
            layoutPassword!!.hint = authUISettings!!.passwordHint
        if (!textIsEmpty(authUISettings!!.nameHint))
            layoutName!!.hint = authUISettings!!.nameHint

        if (authUISettings!!.isSocialPlatformRequired) {
            facebookTv!!.text = Html.fromHtml(authUISettings!!.facebookSignupTitle)
            googleTv!!.text = Html.fromHtml(authUISettings!!.googleSignupTitle)
        }

        if (authUISettings!!.isSignupRequired) {
            signinSignup!!.visibility = View.VISIBLE
            signinSignup!!.text = Html.fromHtml(authUISettings!!.loginToggleTitle)
        } else {
            signinSignup!!.visibility = View.GONE
        }
        proceed!!.text = getString(R.string.signup)
    }

    private fun setForgotPasswordView() {
        layoutName!!.visibility = View.GONE
        layoutMobile!!.visibility = View.GONE
        layoutEmail!!.visibility = View.VISIBLE
        layoutPassword!!.visibility = View.GONE
        title!!.text = Html.fromHtml(authUISettings!!.forgotPasswordTitle)
        belowCont!!.visibility = View.GONE
        signinSignup!!.visibility = View.GONE
        fpLink!!.visibility = View.VISIBLE
        fpLogin!!.text = getString(R.string.loggin)
        fpSignup!!.text = getString(R.string.signup)
        proceed!!.text = getString(R.string.submit)
        if (!authUISettings!!.isSignupRequired)
            fpSignup!!.visibility = View.GONE
    }

    private fun checkErrorMessage(validationMessage: String) {
        if (validationMessage.length != 0) {
            if (authUISettings!!.isHandleFormError) {
                mListener!!.onFormValidationError(validationMessage)
            } else {
                showSnackBar(validationMessage)
            }
        }
    }

    fun showSnackBar(message: String) {
        val snackbar = Snackbar
                .make(activity!!.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun applyTheme() {

        val materialTheme = authUISettings!!.materialTheme
        val materialColor = materialTheme.color

        if (authUISettings!!.bg != 0) {
            bg!!.setImageResource(authUISettings!!.bg)
        } else {
            bg!!.setBackgroundColor(resources.getColor(materialColor.regular))
        }

        title!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        forgotPassword!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        terms!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        fpLogin!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        fpSignup!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        proceed!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))

        email!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        mobile!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        name!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        password!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        or!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))

        proceed!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))
        proceed!!.setBackgroundDrawable(selectorBackgroundColor(resources.getColor(materialColor.light), resources.getColor(materialColor.dark)))

        if (materialTheme == MaterialTheme.INDIGO) {
            fb!!.setBackgroundDrawable(selectorBackgroundColor(resources.getColor(materialColor.light), resources.getColor(materialColor.dark)))
        } else if (materialTheme == MaterialTheme.PINK) {
            google!!.setBackgroundDrawable(selectorBackgroundColor(resources.getColor(materialColor.light), resources.getColor(materialColor.dark)))
        } else if (materialTheme == MaterialTheme.RED) {
            google!!.setBackgroundDrawable(selectorBackgroundColor(resources.getColor(materialColor.light), resources.getColor(materialColor.dark)))
        }

        signinSignup!!.setTextColor(ContextCompat.getColor(context!!, materialColor.textPrimaryColor))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity!!.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(context!!, materialColor.regular)
        }
    }

    companion object {


        val TAG = "AuthUIFragment"

        var AUTHUI_SETTINGS = "authui_settings"

        fun newInstance(authUISettings: AuthUISettings): AuthUIFragment {
            val bundle = Bundle()
            bundle.putParcelable(AUTHUI_SETTINGS, authUISettings)
            val fragment = AuthUIFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }

        fun newInstanceWithDefaultSettings(): AuthUIFragment {
            return AuthUIFragment()
        }

        fun loadFragment(activity: FragmentActivity, f: android.support.v4.app.Fragment, frameId: Int) {
            activity.supportFragmentManager.beginTransaction().add(frameId, f, TAG).commitAllowingStateLoss()
        }

        fun textIsEmpty(value: String?): Boolean {
            if (value == null)
                return true
            var empty = false
            val message = value.trim { it <= ' ' }
            if (TextUtils.isEmpty(message)) {
                empty = true
            }
            val isWhitespace = message.matches("^\\s*$".toRegex())
            if (isWhitespace) {
                empty = true
            }
            return empty
        }

        fun isValidEmail(target: CharSequence?): Boolean {
            return if (target == null) {
                false
            } else {
                android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                        .matches()
            }
        }

        fun selectorBackgroundColor(normal: Int, pressed: Int): StateListDrawable {
            val normalDrawable = GradientDrawable()
            normalDrawable.setColor(normal)
            normalDrawable.cornerRadius = 8f
            val pressedDrawable = GradientDrawable()
            pressedDrawable.setColor(pressed)
            pressedDrawable.cornerRadius = 8f
            val states = StateListDrawable()
            states.addState(intArrayOf(android.R.attr.state_pressed),
                    pressedDrawable)
            states.addState(intArrayOf(), normalDrawable)
            return states
        }
    }

}// Required empty public constructor

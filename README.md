# auth-ui
Android App Auth(Login, Signup and Forgot Password) UI

# What's in the box

- The login, signup and forgot password UI Template for your app
- Easy way to implement
- Play with element visibility
- Setup material theme like Teal, Cyan, Indigo and many more.
- Hide and Show social logins.

<!--[![Download](https://api.bintray.com/packages/sayagodshala/maven/auth-ui/images/download.svg) ](https://bintray.com/sayagodshala/maven/auth-ui/_latestVersion)-->
<!--[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)-->
<!--[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ActionSheet%20for%20Android-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5607)-->
<!--[![TravisCI](https://travis-ci.org/aromajoin/actionsheet-android.svg?branch=master)](https://travis-ci.org/aromajoin/actionsheet-android)-->

<!--[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20Smart%20Login-green.svg?style=true)](https://android-arsenal.com/details/1/3026)-->

<!--![Image](https://raw.githubusercontent.com/CodelightStudios/Android-Smart-Login/master/Screenshots/Info_new.png)-->

Basic Form | Signup without Social Platforms | Login without Social Platforms
---- | ---- | ----
<img width="300" height="533" src="screenshots/1.jpg"> | <img width="300" height="533" src="screenshots/3.jpg"> | <img width="300" height="533" src="screenshots/4.jpg">

### Themes

CYAN | TEAL | WHITE/DEFAULT
---- | ---- | ----
<img width="300" height="533" src="screenshots/cyan.jpg"> | <img width="300" height="533" src="screenshots/teal.jpg"> | <img width="300" height="533" src="screenshots/default.jpg">

# Setup
## 1. Include in your project

### Using Gradle
The **Auth UI** library is pushed to jcenter, so you need to add the following dependency to your app's `build.gradle`.

```gradle
compile 'com.sayagodshala:auth-ui:1.0'
```

### As a module
If you can't include it as gradle dependency, you can also download this GitHub repo and copy the auth-ui folder to your project.


## 2. Usage

First step in configuring the Auth UI Framework is to place `FrameLayout Container` in your layout.

```xml
<FrameLayout
        android:id="@+id/frame"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

Next step is to configure the `AuthUISettings`.

Example:

```java
AuthUISettings authUISettings = new AuthUISettings();
authUISettings.setSocialPlatformRequired(true);
authUISettings.setAppLogoRequired(true);
authUISettings.setTermsRequired(true);
authUISettings.setSignupRequired(true);
authUISettings.setFacebookLoginRequired(true);
authUISettings.setGoogleLoginRequired(true);
authUISettings.setForgotPasswordRequired(true);
authUISettings.setAppLogo(R.mipmap.my_logo);
authUISettings.setLoginTitle("Login using your registered email and password.");
authUISettings.setSignupTitle("You are just few steps a head. Register and start a head.");
authUISettings.setForgotPasswordTitle("Put in your email id for password reset link");
authUISettings.setLoginTerms("By Logging in I agree to the <b>Terms of Use</b>");
authUISettings.setSignupTerms("By Signing up I agree to the <b>Terms of Use</b>");
authUISettings.setFacebookLoginTitle("Login with Facebook");
authUISettings.setFacebookSignupTitle("Signup with Facebook");
authUISettings.setGoogleLoginTitle("Login with Google");
authUISettings.setGoogleSignupTitle("Signup with Google");
authUISettings.setLoginToggleTitle("Have an account? <b>LOGIN</b>");
authUISettings.setSignupToggleTitle("Don\'t have an account? <b>SIGN UP</b>");
authUISettings.setDefaultView(AuthUIView.LOGIN);
authUISettings.setMaterialTheme(MaterialTheme.CYAN);
```
Next step is to load the `AuthUIFragment` in your Activity.

```java
// declare instance of AuthUIFragment
AuthUIFragment authUIFragment;
// load fragment with your own settings or default settings
authUIFragment = AuthUIFragment.newInstance(authUISettings);
**OR**
authUIFragment = AuthUIFragment.newInstanceWithDefaultSettings();

// load authUIFragment into the frame layout
AuthUIFragment.loadFragment(this, authUIFragment, R.id.frame);



```

Final step is to implement `AuthUIFragment.AuthUIFragmentListener` interface in your target activity where `AuthUIFragment` is loaded with corresponding methods.

```java
public class LoginActivity extends AppCompatActivity implements AuthUIFragmentListener {

    @Override
    public void onLoginClicked(AuthUIUser user) {
        switch (user.getLoginType()) {
            case EMAIL:
                Log.d("onLoginClicked", "email : " + user.getEmail() + ", password : " + user.getPassword());
                break;
            case MOBILE:
                Log.d("onLoginClicked", "mobile : " + user.getMobile() + ", password : " + user.getPassword());
                break;
            case EMAIL_OR_MOBILE:
                Log.d("onLoginClicked", "email/mobile : " + user.getEmailOrMobile() + ", password : " + user.getPassword());
                break;
        }
    }

    @Override
    public void onSignupClicked(AuthUIUser user) {
        Log.d("onSignupClicked", "name : " + user.getName() + ", email : " + user.getEmail() + ", mobile : " + user.getMobile() + ", password : " + user.getPassword());
    }

    @Override
    public void onForgotPasswordClicked(AuthUIUser user) {
        Log.d("onForgotPasswordClicked", "email : " + user.getEmail());
        // on successful response of 'forgot password api' call below method to show login view again
        authUIFragment.recallLoginView();
    }

    @Override
    public void onSocialAccountClicked(SocialAccount socialAccount, boolean isRegistration) {
        Log.d("onSocialAccountClicked", "socialAccount : " + socialAccount.name() + ", isRegistration : " + isRegistration);
        switch (socialAccount) {
            case FACEBOOK:
                break;
            case GOOGLE:
                break;
        }
    }

    @Override
    public void onFormValidationError(String error) {
        Log.d("onFormValidationError", "error : " + error);
    }

}
```
This is the simplest way to configure the library to enable Custom login mode along with Social Platform login modes.

**That's it!**

# More about AuthUISettings

Login Types(EMAIL,MOBILE and EMAIL_OR_MOBILE). Default login type is EMAIL.

```java
authUISettings.setLoginType(LoginType.EMAIL);
**OR**
authUISettings.setLoginType(LoginType.MOBILE);
**OR**
authUISettings.setLoginType(LoginType.EMAIL_OR_MOBILE);
```

To hide social platforms use below code

```java
authUISettings.setSocialPlatformRequired(false);
```

To hide App logo

```java
authUISettings.setAppLogoRequired(false);
```

In case your app only requires login

```java
authUISettings.setSignupRequired(false);
```

In case your app only requires one of the below

```java
authUISettings.setFacebookLoginRequired(false);
**OR**
authUISettings.setGoogleLoginRequired(false);
```

In case you want to handle the form validation error message

```java
authUISettings.setHandleFormError(true);
// once it is set to true, implement your logic in 'onFormValidationError(String message)' method of AuthUIFragmentListener interface
```

In case your app doesn't require forgot password

```java
authUISettings.setForgotPasswordRequired(true);
```

Set your default view

```java
authUISettings.setDefaultView(AuthUIView.LOGIN);
**OR**
authUISettings.setDefaultView(AuthUIView.SIGNUP);
```

Supported Themes (Default Theme is WHITE)

```java
authUISettings.setMaterialTheme(MaterialTheme.DEFAULT);
**OR**
authUISettings.setMaterialTheme(MaterialTheme.WHITE);
**OR**
authUISettings.setMaterialTheme(MaterialTheme.RED);
```

and many more like PINK,PURPLE,DEEP_PURPLE,INDIGO,BLUE,LIGHT_BLUE,CYAN,TEAL,BLUE_GREY,YELLOW,AMBER,BROWN,GREEN and GREY.

# Included Libraries
The following third-party libraries were used in this framework.

- GSON library


# Contribution
All contributions are welcome. Encounter any issue? Don't hesitate to [open an issue](https://github.com/sayagodshala/auth-ui/issues)

Convention: **Master branch** would be the development branch. So feel free to fork from the Master branch.

# License

    Copyright 2017 Codelight Studios

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
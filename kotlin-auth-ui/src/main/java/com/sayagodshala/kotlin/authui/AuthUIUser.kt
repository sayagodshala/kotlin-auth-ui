package com.sayagodshala.kotlin.authui

class AuthUIUser {

    var name: String? = null
    var email: String? = null
    var mobile: String? = null
    var emailOrMobile: String? = null
    var password: String? = null
    var loginType: LoginType? = null

    constructor() {}

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }

    constructor(name: String, emailOrMobile: String, password: String) {
        this.name = name
        this.emailOrMobile = emailOrMobile
        this.password = password
    }

    constructor(email: String) {
        this.email = email
    }

    constructor(name: String, email: String, mobile: String, password: String) {
        this.name = name
        this.email = email
        this.mobile = mobile
        this.password = password
    }
}

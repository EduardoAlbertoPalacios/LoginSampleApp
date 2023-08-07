package com.example.loginsampleapp.utils

fun String.isValidEmail(): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return matches(emailRegex)
}

fun String.isNotValidEmail(): Boolean = this.isValidEmail().not()

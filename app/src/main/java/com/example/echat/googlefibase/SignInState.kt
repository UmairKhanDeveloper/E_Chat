package com.example.echat.googlefibase


data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
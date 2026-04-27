package com.example.echat.firebaseauth

data class AuthUser(
    val username : String? ="",
    val email: String? = "",
    val password: String? = "",
)
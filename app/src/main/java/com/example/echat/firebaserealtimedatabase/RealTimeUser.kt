package com.example.echat.firebaserealtimedatabase

data class RealTimeUser(
    val items: RealTimeItems,
    val key: String? = ""
) {
    data class RealTimeItems(
        var photo: String = "",
        var userFistName: String = "",
        var email: String = "",
        var password: String = "",

    ) {
        constructor() : this( "", "", "", "")
    }
}
package com.example.echat.firebaserealtimedatabase

data class RealTimeUser(
    val items: RealTimeItems,
    val key: String? = ""
) {
    data class RealTimeItems(
        var photo: String = "",
        var userFistName: String = "",
        var userLastName: String = "",
        var email: String = "",
        var password: String = "",
        var phoneNumber: String = "",
        var age: Int = 0,
        var gender: String = ""
    ) {
        constructor() : this("", "", "", "", "", "", 0, "")
    }
}
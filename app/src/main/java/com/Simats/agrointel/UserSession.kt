package com.Simats.agrointel

data class UserSession(
    val fullName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val farmName: String = "",
    val totalLand: String = "",
    val state: String = "",
    val district: String = "",
    val village: String = ""
)
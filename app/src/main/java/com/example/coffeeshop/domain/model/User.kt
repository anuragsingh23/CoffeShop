package com.example.coffeeshop.domain.model

import androidx.room.PrimaryKey

data class User(
    val name: String,
    val phoneNumber: String,
    val userID: String? = null,
)
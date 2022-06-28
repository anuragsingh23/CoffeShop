package com.example.coffeeshop.domain.model

import androidx.room.PrimaryKey

data class User( val name : String , @PrimaryKey  val phoneNumber : String
)
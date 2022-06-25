package com.example.coffeeshop.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_orders")
data class Order(

    @PrimaryKey(autoGenerate = true) val id: Int ,
    val items : String ,
    val size : String ,
    val note: String? = null,
    val amount: Int,
    val date : String
)

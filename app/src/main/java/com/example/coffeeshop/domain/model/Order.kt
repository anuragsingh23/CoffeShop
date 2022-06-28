package com.example.coffeeshop.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "order")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int? = null ,
    val items : String ,
    val size : String ,
    val note: String?,
    val amount: Int,
    val date : String
)

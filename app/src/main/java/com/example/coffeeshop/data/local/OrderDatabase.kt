package com.example.coffeeshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeshop.domain.model.Order


@Database(
    entities = [Order::class],
    version = 1 ,
    exportSchema = false
)
abstract class OrderDatabase : RoomDatabase() {

    abstract val orderDao : OrderDao

}
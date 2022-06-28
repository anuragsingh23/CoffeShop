package com.example.coffeeshop.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coffeeshop.domain.model.Order
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Query("SELECT * FROM `order`")
    fun getOrders(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)
}
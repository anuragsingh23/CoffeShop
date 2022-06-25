package com.example.coffeeshop.domain.repo

import com.example.coffeeshop.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrders() : Flow<List<Order>>

    suspend fun insertOrder(order: Order)
}
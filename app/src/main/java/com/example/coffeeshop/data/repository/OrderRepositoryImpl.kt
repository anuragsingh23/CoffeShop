package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.data_source.OrderDao
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.repo.OrderRepository
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val dao: OrderDao
) : OrderRepository{

    override fun getOrders(): Flow<List<Order>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOrder(order: Order) {
        dao.insertItems(order)
    }
}
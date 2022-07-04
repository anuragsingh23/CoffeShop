package com.example.coffeeshop.domain.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.coffeeshop.domain.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect


class FakeOrderRepository : OrderRepository {

    private val orders = mutableListOf<Order>()

    private val getAllOrder = MutableLiveData<List<Order>>(orders).asFlow()


    override fun getOrder(): Flow<List<Order>> {
        return getAllOrder
    }

    override suspend fun insertOrder(order: Order) {
        orders.add(order)
    }
}
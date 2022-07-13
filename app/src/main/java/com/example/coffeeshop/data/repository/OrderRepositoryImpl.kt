package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.local.OrderDao
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.repo.OrderRepository
import com.example.coffeeshop.utils.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val dao: OrderDao,
) : OrderRepository {

    private lateinit var databaseReference: DatabaseReference

    private val _order: MutableList<Order> = mutableListOf()
    private val order: List<Order> = _order

    override fun getOrder(): Flow<List<Order>> {
        return dao.getOrders()
    }

    override suspend fun insertOrder(order: Order) {
        return dao.insertOrder(order)
    }

    init {
        databaseReference.child("Orders").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                // do nothing for now
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (i in p0.children) {
                        val item = i.getValue(Order::class.java)
                        _order.add(item!!)
                    }
                    Resource.Success("Data fetched successfully")
                } else {
                    Resource.Error("Unknown error occurred", null)
                }
            }
        })
    }
}
package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.data_source.OrderDao
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.repo.OrderRepository
import com.example.coffeeshop.utils.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val dao : OrderDao
) : OrderRepository {

    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child("orders")
    private var items: ArrayList<Order> = arrayListOf()

    override fun getOrder(): Flow<List<Order>> {
        return dao.getOrders()
    }

    override suspend fun insertOrder(order: Order) {
            return dao.insertOrder(order)
    }
    init {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                // do nothing for now
            }

            override fun onDataChange(p0: DataSnapshot) {

                items = ArrayList()
                if (p0.exists()) {
                    for (i in p0.children) {
                        val itm = i.getValue(Order::class.java)
                        items.add(itm!!)
                    }
                    Resource.Success("Data fetched successfully")
                }
                else{
                    Resource.Error("Unknown error occurred", null)

                }
            }
        })
    }
}
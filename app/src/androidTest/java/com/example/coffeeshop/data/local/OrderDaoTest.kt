package com.example.coffeeshop.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.coffeeshop.domain.model.Order
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest

class OrderDaoTest {
    private lateinit var database: OrderDatabase
    private lateinit var dao : OrderDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            OrderDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.orderDao

    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun readAndWriteData_returnTrue(): Unit = runBlocking {
        val order = Order("2", "small" , "3", "small"
            , "3","small","4","small",
            "small","300","30-4-2000")
        dao.insertOrder(order)
        val orders = dao.getOrders().first()
        assertThat(orders.contains(order))
    }
}
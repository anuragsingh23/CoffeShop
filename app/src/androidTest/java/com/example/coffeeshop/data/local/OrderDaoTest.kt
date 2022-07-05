package com.example.coffeeshop.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.launchFragmentInHiltContainer
import com.example.coffeeshop.ui.firstscreen.FirstFragment
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class OrderDaoTest {

    @get:Rule
    var hiltRule =HiltAndroidRule(this)
    @Inject
    @Named("test_db")
    lateinit var database: OrderDatabase
    private lateinit var dao : OrderDao

    @Before
    fun setup(){
        hiltRule.inject()
        dao = database.orderDao

    }

    @After
    fun tearDown(){
        database.close()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLaunchFragmentInHiltContainer()  {
        launchFragmentInHiltContainer<FirstFragment> {
        }
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
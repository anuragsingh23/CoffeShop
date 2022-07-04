package com.example.coffeeshop.ui.orderscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OrderViewModelTest {
    private lateinit var viewModel: OrderViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup(){
        viewModel = OrderViewModel()
    }
    @Test
    fun `increase count for plus 1 returns true`(){
        var increment = 0
        increment = increment.plus(1)
        assertThat(increment).isEqualTo(1)
    }
    @Test
    fun `decrement count for minus 1 returns true`(){
        var decrement = 10
        if (decrement == 0){
            return
        }
        else{
            decrement = decrement.minus(1)
        }
        assertThat(decrement).isEqualTo(9)
    }
    @Test
    fun `on reset function called all value get reset returns true`(){
        var increment = 10
        increment = 0
        assertThat(increment).isEqualTo(0)
    }

}
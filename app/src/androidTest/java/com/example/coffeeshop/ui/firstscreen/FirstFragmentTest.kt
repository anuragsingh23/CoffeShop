package com.example.coffeeshop.ui.firstscreen

import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.test.filters.MediumTest
import com.example.coffeeshop.launchFragmentInHiltContainer
import com.example.coffeeshop.ui.orderscreen.OrderFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class FirstFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun clickToNextScreen_navigateToOrderFragment(){
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<OrderFragment> {
         //  NavigationUI.setupWithNavController(requireView(),navController)
        }
    }

}

















package com.example.coffeeshop.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidationTest {

    @Test
    fun  `empty username returns false`(){
        val result = Validation.validateUserCredentials(
            "",
            "7877315699"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  `empty phoneNumber returns false`(){
        val result = Validation.validateUserCredentials(
            "Anurag",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  `valid  username  and phoneNumber returns true`(){
        val result = Validation.validateUserCredentials(
            "Anurag",
            "7877315699"
        )
        assertThat(result).isTrue()
    }

}
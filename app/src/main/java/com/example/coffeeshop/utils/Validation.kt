package com.example.coffeeshop.utils

object Validation {

    /**
     * the input is not valid if..
     * username / phoneNumber is empty
     * phoneNumber is less than 10 digit
     */
    fun validateUserCredentials(
        name : String ,
        phoneNumber : String
    ):Boolean {
        if (name.isEmpty() || phoneNumber.isEmpty()){
            return false
        }
        return true
    }
}
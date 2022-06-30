package com.example.coffeeshop.utils

import android.icu.text.SimpleDateFormat
import java.util.*

object Time {
    fun getCurrentTime() : String {
        return SimpleDateFormat("dd-mm-yyyy' \n 'HH:mm' ").format(Calendar.getInstance().time)
    }
}

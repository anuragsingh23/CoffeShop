package com.example.coffeeshop.utils

import android.icu.text.SimpleDateFormat
import java.util.*


fun getCurrentTime() : String {
    return SimpleDateFormat("yyyy-MM-dd' T 'HH:mm' Z '").format(Calendar.getInstance().time)
}
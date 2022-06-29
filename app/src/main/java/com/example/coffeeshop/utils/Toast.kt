package com.example.coffeeshop.utils

import android.widget.Toast
import com.example.coffeeshop.app.CoffeeApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

object Toast {

    private val applicationScope = CoroutineScope(SupervisorJob())

    @OptIn(ExperimentalTime::class)
    private var toastTimeMark = TimeSource.Monotonic.markNow()

    @OptIn(ExperimentalTime::class)
    fun showToast(message: String) {
        applicationScope.launch(Dispatchers.Main) {
            Toast.makeText(CoffeeApp.instance, message, Toast.LENGTH_SHORT).show()
            toastTimeMark = TimeSource.Monotonic.markNow()
        }
    }
}
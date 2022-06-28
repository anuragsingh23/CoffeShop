package com.example.coffeeshop.ui.historyscreen

import androidx.lifecycle.ViewModel
import com.example.coffeeshop.data.repository.OrderRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor ( private val repository: OrderRepositoryImpl ) : ViewModel() {



}
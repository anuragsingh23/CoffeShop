package com.example.coffeeshop.ui.historyscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.coffeeshop.domain.repo.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor
    ( private val repository: OrderRepository
    ) : ViewModel() {



 //   var orders = repository.getOrder().asLiveData()


}
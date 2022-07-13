package com.example.coffeeshop.ui.historyscreen

import androidx.lifecycle.*
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.repo.OrderRepository
import com.example.coffeeshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor
    ( private val repository: OrderRepository
    ) : ViewModel() {


     var items: LiveData<List<Order>>? = null

    // Called the moment ViewModel is created
    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            items = repository.getOrder().asLiveData()
        }
    }
}

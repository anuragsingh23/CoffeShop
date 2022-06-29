package com.example.coffeeshop.ui.orderscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeshop.utils.Toast
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_DefaultViewModelFactories_ActivityEntryPoint

class OrderViewModel : ViewModel() {

    private val _espresso = MutableLiveData<Int>()
    val espresso : LiveData<Int> = _espresso

    private val _cappa = MutableLiveData<Int>()
    val cappa : LiveData<Int> = _cappa

    private val _latte = MutableLiveData<Int>()
    val latte : LiveData<Int> = _latte

    private val _frappe = MutableLiveData<Int>()
    val frappe : LiveData<Int> = _frappe

    init {
        _espresso.value = 0
        _cappa.value = 0
        _latte.value = 0
        _frappe.value = 0
    }

    fun incrementEspresso(){
        _espresso.value = espresso.value?.plus(1)
    }

    fun decreaseEspresso(){
        if (_espresso.value==0){
            return
        }
        else {
            _espresso.value = espresso.value?.minus(1)
        }
    }
    fun incrementCappa(){
        _cappa.value = cappa.value?.plus(1)
    }

    fun decreaseCappa(){
        if (_cappa.value==0){
            return
        }
        else {
            _cappa.value = cappa.value?.minus(1)
        }
    }
    fun incrementLatte(){
        _latte.value = latte.value?.plus(1)
    }

    fun decreaseLatte(){
        if (_latte.value==0){
            return
        }
        else {
            _latte.value = latte.value?.minus(1)
        }
    }
    fun incrementFrappe(){
        _frappe.value = frappe.value?.plus(1)
    }

    fun decreaseFrappe(){
        if (_frappe.value==0){
            return
        }
        else {
            _frappe.value = frappe.value?.minus(1)
        }
    }

    fun reset(){
        _espresso.value = 0
        _cappa.value = 0
        _frappe.value = 0
        _latte.value = 0
    }
}
package ru.rache.mobileappmanager.ui.purchases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PurchasesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Тут будет страница покупок"
    }
    val text: LiveData<String> = _text
}
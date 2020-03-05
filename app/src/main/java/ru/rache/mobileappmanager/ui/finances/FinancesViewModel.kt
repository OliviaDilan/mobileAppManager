package ru.rache.mobileappmanager.ui.finances

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinancesViewModel : ViewModel() {

        private val _text = MutableLiveData<String>().apply {
        value = "Тут будет страница учета бюджета"
    }
    val text: LiveData<String> = _text

}

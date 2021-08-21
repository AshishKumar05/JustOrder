package com.example.justorder.ui.homefragments.myorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyOrderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is myOrder Fragment"
    }
    val text: LiveData<String> = _text
}
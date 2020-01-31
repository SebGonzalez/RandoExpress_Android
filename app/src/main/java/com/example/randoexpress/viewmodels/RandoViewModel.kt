package com.example.randoexpress.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RandoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Main view model"
    }


    val text: LiveData<String> = _text
}
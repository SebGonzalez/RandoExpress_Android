package com.example.randoexpress.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randoexpress.model.Model

class RandoViewModel : ViewModel() {

    val selected = MutableLiveData<Model.Rando>()

    fun select(item: Model.Rando) {
        selected.value = item
    }

}
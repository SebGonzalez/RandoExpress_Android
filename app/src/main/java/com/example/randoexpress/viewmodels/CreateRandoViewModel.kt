package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class CreateRandoViewModel(jwt: String, rando: Model.Rando): ViewModel()  {
    val repository: RandoRepository = RandoRepository()

    val addRando = liveData(Dispatchers.IO) {
        val message = repository.addRando(jwt, rando)
        Log.i("====>CreateRando", "ADDING NEW HIKE")
        emit(message)
    }
}
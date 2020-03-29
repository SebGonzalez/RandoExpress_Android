package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class CreateRandoViewModel(): ViewModel()  {

    private val repository: RandoRepository = RandoRepository()
    var jwt: String =""
    var rando: Model.Rando = Model.Rando()

    val addRando = liveData(Dispatchers.IO) {
        val message = repository.addRando(jwt, rando)
        Log.i("==VM==>CreateRandoVM", "ADDING NEW HIKE")
        emit(message)
    }
}
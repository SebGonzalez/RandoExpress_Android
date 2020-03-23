package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class SubscriptionViewModel(jwt: String, id: Int, email: String): ViewModel() {
    val repository: RandoRepository = RandoRepository()

    val subscribe = liveData(Dispatchers.IO) {
        val message = repository.subscribe(jwt, id, email)
        Log.i("====>AuthViewModel", "Signup message: "+message)
        emit(message)
    }

    val unsubscribe = liveData(Dispatchers.IO) {
        val message = repository.unsubscribe(jwt, id, email)
        Log.i("====>AuthViewModel", "Signup message: "+message)
        emit(message)
    }


}
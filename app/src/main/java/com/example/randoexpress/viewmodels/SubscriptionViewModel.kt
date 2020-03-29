package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class SubscriptionViewModel(): ViewModel() {

    private val repository: RandoRepository = RandoRepository()
    var jwt: String = ""
    var id: Int = 0
    var email: String = ""

    val subscribe = liveData(Dispatchers.IO) {
        val message = repository.subscribe(jwt, id, email)
        Log.i("==VM==>subscribeVM", "Signup message: "+message)
        emit(message)
    }

    val unsubscribe = liveData(Dispatchers.IO) {
        val message = repository.unsubscribe(jwt, id, email)
        Log.i("==VM==>unsubscribeVM", "Signup message: "+message)
        emit(message)
    }


}
package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model.SignUpUser
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class AuthViewModel(): ViewModel() {

    private val repository: RandoRepository = RandoRepository()
    var user: SignUpUser = SignUpUser()

    val signupUser = liveData(Dispatchers.IO) {
        val message = repository.signupUser(user)
        Log.i("==VM==>AuthViewModel", "Signup message: "+message)
        emit(message)
    }


}
package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model
import com.example.randoexpress.model.Model.SignUpUser
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class AuthViewModel(user: SignUpUser): ViewModel() {
    val repository: RandoRepository = RandoRepository()

    val signupUser = liveData(Dispatchers.IO) {
        val message = repository.signupUser(user)
        Log.i("====>AuthViewModel", "Signup message: "+message)
        emit(message)
    }


}
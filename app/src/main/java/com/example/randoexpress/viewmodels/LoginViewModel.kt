package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(user: Model.LoginUser): ViewModel(){
    val repository: RandoRepository = RandoRepository()

    val loginUser = liveData(Dispatchers.IO) {
        val loginData = repository.loginUser(user)
        Log.i("====>LoginViewModel", "Login message: "+loginData)
        emit(loginData)
    }
}
package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(): ViewModel(){

    private val repository: RandoRepository = RandoRepository()
    var user: Model.LoginUser = Model.LoginUser()

    val loginUser = liveData(Dispatchers.IO) {
        Log.i("==VM==>LoginViewModel", "Login data: "+user)
        val loginData = repository.loginUser(user)
        Log.i("==VM==>LoginViewModel", "Login message: "+loginData)
        emit(loginData)
    }
}
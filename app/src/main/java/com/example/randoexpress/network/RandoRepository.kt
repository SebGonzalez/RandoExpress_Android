package com.example.randoexpress.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randoexpress.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandoRepository {
    private var client: RandoService = webservice

    suspend fun getRandoList() = client.getRandos()
}
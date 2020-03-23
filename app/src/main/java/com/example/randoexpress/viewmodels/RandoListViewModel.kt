package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher


class RandoListViewModel(jwt: String, id: Int = 0) : ViewModel(){
    val repository: RandoRepository = RandoRepository()

    val getRandoList = liveData(Dispatchers.IO) {
        val randoList = repository.getRandoList(jwt)
        Log.i("====>getRandoList", "FETCHING DATA")
        emit(randoList)
    }

    val getRando = liveData(Dispatchers.IO) {
        val rando = repository.getRando(jwt, id)
        Log.i("====>getRandoList", "FETCHING DATA")
        emit(rando)
    }

    val getFutureRandoList = liveData(Dispatchers.IO) {
        val randoList = repository.getFutureRandoList(jwt, id)
        Log.i("====>getFutureRandoList", "FETCHING DATA")
        emit(randoList)
    }

    val getPastRandoList = liveData(Dispatchers.IO) {
        val randoList = repository.getPastRandoList(jwt, id)
        Log.i("====>getPastRandoList", "FETCHING DATA")
        emit(randoList)
    }
}
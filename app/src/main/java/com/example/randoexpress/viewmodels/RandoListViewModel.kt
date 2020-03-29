package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers


class RandoListViewModel() : ViewModel(){

    private val repository: RandoRepository = RandoRepository()
    var jwt: String = ""
    var id: Int = 0

    val getRandoList = liveData(Dispatchers.IO) {
        val randoList = repository.getRandoList(jwt)
        Log.i("==VM==>getRandoListVM", "FETCHING DATA")
        emit(randoList)
    }

    val getRando = liveData(Dispatchers.IO) {
        val rando = repository.getRando(jwt, id)
        Log.i("==VM==>getRandoVM", "FETCHING DATA")
        emit(rando)
    }

    val getFutureRandoList = liveData(Dispatchers.IO) {
        val randoList = repository.getFutureRandoList(jwt, id)
        Log.i("==VM==>futureRandoList", "FETCHING DATA")
        emit(randoList)
    }

    val getPastRandoList = liveData(Dispatchers.IO) {
        val randoList = repository.getPastRandoList(jwt, id)
        Log.i("==VM==>getPastRandoList", "FETCHING DATA")
        emit(randoList)
    }
}
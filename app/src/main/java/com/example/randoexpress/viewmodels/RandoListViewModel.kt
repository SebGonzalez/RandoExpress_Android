package com.example.randoexpress.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher


class RandoListViewModel : ViewModel(){
    val repository: RandoRepository = RandoRepository()

    val getRandoList = liveData(Dispatchers.IO) {
        val randoList = repository.getRandoList()
        emit(randoList)
    }
}
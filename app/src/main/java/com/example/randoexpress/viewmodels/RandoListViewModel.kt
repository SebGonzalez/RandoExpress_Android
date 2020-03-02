package com.example.randoexpress.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoService
import com.example.randoexpress.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RandoListViewModel : ViewModel() {

    // TO DO
    val randoList = MutableLiveData<ArrayList<Model.Rando>>()
    val selectedRando = MutableLiveData<Model.Rando>()

    init {
        val service: RandoService =
            RetrofitInstance.getRetrofitInstance()!!.create(RandoService::class.java)
        val call: Call<ArrayList<Model.Rando>> = service.getRandos()
        call.enqueue(object : Callback<ArrayList<Model.Rando>> {
            override fun onResponse(
                call: Call<ArrayList<Model.Rando>>,
                response: Response<ArrayList<Model.Rando>>
            ) {
                if (response.isSuccessful()) {
                    randoList.value = response.body()
                } else {
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<ArrayList<Model.Rando>>, t: Throwable) {
                Log.e("====>MainActivity", "Something went wrong $t")
            }
        })
    }
}
package com.example.randoexpress.network

import com.example.randoexpress.model.Model
import retrofit2.Call
import retrofit2.http.GET

interface RandoService {
    @GET("/bins/tbx60")
    fun getRandos(): Call<ArrayList<Model.Rando>>?
}
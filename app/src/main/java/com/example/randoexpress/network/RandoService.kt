package com.example.randoexpress.network

import com.example.randoexpress.model.Model
import retrofit2.http.GET

interface RandoService {
    @GET("/bins/1b3cma")
    suspend fun getRandos(): ArrayList<Model.Rando>
}
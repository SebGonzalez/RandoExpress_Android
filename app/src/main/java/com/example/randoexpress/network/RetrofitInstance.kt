package com.example.randoexpress.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val webservice: RandoService by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.myjson.com")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build().create(RandoService::class.java)
}
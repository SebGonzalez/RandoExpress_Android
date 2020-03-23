package com.example.randoexpress.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val webservice: RandoService by lazy {
    Retrofit.Builder()
        .baseUrl("http://ec2-54-224-135-228.compute-1.amazonaws.com:8080")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build().create(RandoService::class.java)
}

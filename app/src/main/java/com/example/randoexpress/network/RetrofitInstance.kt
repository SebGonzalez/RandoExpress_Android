package com.example.randoexpress.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.myjson.com"


    fun getRetrofitInstance(): Retrofit? {
        val client = OkHttpClient.Builder()
            .build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit
    }
}
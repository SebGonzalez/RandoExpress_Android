package com.example.randoexpress.model

import retrofit2.http.Query

object Model {
    data class Rando(val ville: String,
                     val name: String,
                     val description: String,
                     val id: Int,
                     val dateDepart: String,
                     val latitude: String,
                     val longitude: String)
    data class User(val firstName: String,
                    val name: String,
                    val id: Int)
}
package com.example.randoexpress.model


object Model {
    data class Rando(val ville: String,
                     val name: String,
                     val description: String,
                     val id: Int,
                     val dateDepart: String,
                     val heureDepart: String,
                     val latitude: String,
                     val longitude: String,
                     val owner: User,
                     val persons: Array<User>
                     )
    data class User(val firstName: String,
                    val name: String,
                    val id: Int)
}
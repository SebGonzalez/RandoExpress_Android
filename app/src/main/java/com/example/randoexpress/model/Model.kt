package com.example.randoexpress.model


object Model {
    data class Rando(val ville: String = "",
                     val name: String = "",
                     val description: String = "",
                     val id: Int? = null,
                     val dateDepart: String = "",
                     val heureDepart: String = "",
                     val latitude: String ="",
                     val longitude: String = "",
                     val owner: User = User(),
                     val persons: Array<User> = arrayOf()
    )
    data class User(val firstName: String = "",
                    val name: String = "",
                    val id: Int = 0,
                    val jwt: String = "",
                    val message: String = "",
                    val mail: String = "",
                    val password: String = ""
    )
    data class SignUpUser(val firstName: String = "",
                          val name: String = "",
                          val password: String = "",
                          val mail: String = ""
    )
    data class LoginUser(val mail: String = "",
                         val password: String = ""
    )
    data class Message(val message: String)
}
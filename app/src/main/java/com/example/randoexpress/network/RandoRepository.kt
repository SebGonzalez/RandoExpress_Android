package com.example.randoexpress.network

import com.example.randoexpress.model.Model

class RandoRepository {
    private var client: RandoService = webservice

    // list of all available hikes
    suspend fun getRandoList(jwt: String) = client.getRandos(jwt)
    //selected hike information
    suspend fun getRando(jwt: String, id: Int) = client.getRando(jwt, id)
    // all future hikes of the user
    suspend fun getFutureRandoList(jwt: String, id: Int) = client.getFutureRandos(jwt, id)
    // all past hikes of the user
    suspend fun getPastRandoList(jwt: String, id: Int) = client.getPastRandos(jwt, id)
    // creates a new hike
    suspend fun addRando(jwt: String, rando: Model.Rando) = client.addRando(jwt, rando)

    // creates new user in DB
    suspend fun signupUser(user: Model.SignUpUser) = client.auth(user)
    // returns jwt token to access API
    suspend fun loginUser(user: Model.LoginUser) = client.login(user)

    // subscribes user to the hike
    suspend fun subscribe(jwt: String, id: Int, email: String) = client.subscribe(jwt, id, email)
    // unsubscribes user from the hike
    suspend fun unsubscribe(jwt: String, id: Int, email: String) = client.unsubscribe(jwt, id, email)

}
package com.example.randoexpress.network

import com.example.randoexpress.model.Model

class RandoRepository {
    private var client: RandoService = webservice

    suspend fun getRandoList(jwt: String) = client.getRandos(jwt)
    suspend fun getRando(jwt: String, id: Int) = client.getRando(jwt, id)
    suspend fun getFutureRandoList(jwt: String, id: Int) = client.getFutureRandos(jwt, id)
    suspend fun getPastRandoList(jwt: String, id: Int) = client.getPastRandos(jwt, id)
    suspend fun addRando(jwt: String, rando: Model.Rando) = client.addRando(jwt, rando)

    suspend fun signupUser(user: Model.SignUpUser) = client.auth(user)
    suspend fun loginUser(user: Model.LoginUser) = client.login(user)

    suspend fun subscribe(jwt: String, id: Int, email: String) = client.subscribe(jwt, id, email)
    suspend fun unsubscribe(jwt: String, id: Int, email: String) = client.unsubscribe(jwt, id, email)

}
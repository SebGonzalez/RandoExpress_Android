package com.example.randoexpress.network

import com.example.randoexpress.model.Model
import retrofit2.http.*

interface RandoService {
    @GET("/RandoExpress_API/ws/rest/randos")
    suspend fun getRandos(@Header("Authorization") jwt: String): ArrayList<Model.Rando>

    @GET("/RandoExpress_API/ws/rest/rando/id/{id}")
    suspend fun getRando(@Header("Authorization") jwt: String,
                         @Path("id") id: Int): Model.Rando

    @GET("/RandoExpress_API/ws/rest/randos/person/{id}")
    suspend fun getFutureRandos(@Header("Authorization") jwt: String,
                                @Path("id") id: Int): ArrayList<Model.Rando>

    @GET("/RandoExpress_API/ws/rest/randos/person/old/{id}")
    suspend fun getPastRandos(@Header("Authorization") jwt: String,
                              @Path("id") id: Int): ArrayList<Model.Rando>

    @Headers("Content-Type:application/json")
    @POST("/RandoExpress_API/ws/rest/personne")
    suspend fun auth(@Body user: Model.SignUpUser): Model.Message

    @Headers("Content-Type:application/json")
    @POST("/RandoExpress_API/ws/rest/auth")
    suspend fun login(@Body user: Model.LoginUser): Model.User

    @POST("/RandoExpress_API/ws/rest/rando/{id}/inscription/{mail}")
    suspend fun subscribe(@Header("Authorization") jwt: String,
                          @Path("id") id: Int,
                          @Path("mail") email: String): Model.Message

    @POST("/RandoExpress_API/ws/rest/rando/{id}/desinscription/{mail}")
    suspend fun unsubscribe(@Header("Authorization") jwt: String,
                            @Path("id") id: Int,
                            @Path("mail") email: String): Model.Message
}
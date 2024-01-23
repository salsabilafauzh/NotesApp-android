package com.example.notesapp.api

import com.example.notesapp.model.ResponseApi
import com.example.notesapp.model.UserCreateResponse
import com.example.notesapp.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Users {
//    @GET("/users/{{currentUserId}}")
//    suspend fun getUserById(currentUserId: String): Response<ResponseApi<UserModel>>

//    @FormUrlEncoded
    @POST("/users")
    suspend fun postNewUser(@Body user: UserModel): Response<ResponseApi<UserCreateResponse>>
}
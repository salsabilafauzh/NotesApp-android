package com.example.notesapp.repository

import com.example.notesapp.api.Users
import com.example.notesapp.model.ResponseApi
import com.example.notesapp.model.UserCreateResponse
import com.example.notesapp.model.UserModel
import retrofit2.Response
import retrofit2.Retrofit

class UserRepository(private  val retrofit:Retrofit) {
    private val userApi:Users = retrofit.create(Users::class.java)

    suspend fun createUser(user:UserModel): Response<ResponseApi<UserCreateResponse>> {
        return userApi.postNewUser(user)
    }
}
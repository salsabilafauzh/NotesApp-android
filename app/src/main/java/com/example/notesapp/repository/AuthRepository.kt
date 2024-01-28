package com.example.notesapp.repository

import com.example.notesapp.api.Auth
import com.example.notesapp.model.AuthModel
import com.example.notesapp.model.ResponseApi
import com.example.notesapp.model.data.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit

class AuthRepository(private val retrofit: Retrofit) {
    private val authApi:Auth = retrofit.create(Auth::class.java)

    suspend fun postLogin(user: AuthModel): Response<ResponseApi<AuthResponse>> {
        return authApi.postLogin(user)
    }
}
package com.example.notesapp.api

import com.example.notesapp.model.AuthModel
import com.example.notesapp.model.ResponseApi
import com.example.notesapp.model.data.AuthResponse
import com.example.notesapp.model.data.ListNote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Auth {
    @POST("/authentications")
    suspend fun postLogin(@Body user: AuthModel): Response<ResponseApi<AuthResponse>>
}
package com.example.notesapp.api

import android.adservices.adid.AdId
import com.example.notesapp.model.Note
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotesService {
    @POST("/notes")
    @FormUrlEncoded
    suspend fun postNote(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("tags") tags: Array<String>
    ): Response<Note>

    @GET("/notes")
    suspend fun getNotes(): Response<List<Note>>

    @GET("/notes/{noteId}")
    suspend fun getNoteById(@Path("id") userId: String, @Body bodyNote: Note): Response<Note>

    @PUT("/notes/{noteId}")
    suspend fun updateNoteById(@Path("id") userId: String, @Body bodyNote: Note): Response<Note>

}
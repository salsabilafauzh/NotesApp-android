package com.example.notesapp.api

import com.example.notesapp.model.NoteModel
import com.example.notesapp.model.ResponseApi
import com.example.notesapp.model.data.ListNote
import com.example.notesapp.model.data.NotePostPut
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Notes {
    @POST("/notes")
    @FormUrlEncoded
    suspend fun postNote(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("tags") tags: Array<String>
    ): ResponseApi<NotePostPut>

    @GET("/notes")
    suspend fun getNotes(): ResponseApi<ListNote>

    @GET("/notes/{noteId}")
    suspend fun getNoteById(@Path("id") userId: String, @Body bodyNote: NoteModel): ResponseApi<NoteModel>

    @PUT("/notes/{noteId}")
    suspend fun updateNoteById(@Path("id") userId: String, @Body bodyNote: NoteModel): ResponseApi<NoteModel>

}
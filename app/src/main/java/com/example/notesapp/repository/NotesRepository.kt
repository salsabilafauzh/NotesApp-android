package com.example.notesapp.repository

import com.example.notesapp.api.Notes
import com.example.notesapp.model.ResponseApi
import com.example.notesapp.model.data.ListNote
import retrofit2.Retrofit

class NotesRepository(private val retrofit: Retrofit) {
    private val notesApi: Notes = retrofit.create(Notes::class.java)

    suspend fun getNotes(): ResponseApi<ListNote> {
        return notesApi.getNotes()
    }
}
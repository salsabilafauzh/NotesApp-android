package com.example.notesapp.model

import com.google.gson.annotations.SerializedName

data class NoteModel(
    @SerializedName("body") val body: String,
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("title") val title: String,
    @SerializedName("updatedAt") val updatedAt: String?
)
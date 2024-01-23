package com.example.notesapp.model

data class Note(
    val body: String,
    val createdAt: String,
    val id: String,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)
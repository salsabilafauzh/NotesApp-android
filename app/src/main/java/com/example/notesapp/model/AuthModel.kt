package com.example.notesapp.model

import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("password") val password: String,
    @SerializedName("username") val username: String
)
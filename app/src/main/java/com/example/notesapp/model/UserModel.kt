package com.example.notesapp.model

import com.google.gson.annotations.SerializedName


data class UserModel(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("fullname") val fullname: String
)


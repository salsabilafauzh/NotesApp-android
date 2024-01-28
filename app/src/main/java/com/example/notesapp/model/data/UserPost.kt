package com.example.notesapp.model.data

import com.google.gson.annotations.SerializedName

data class UserPost(
    @SerializedName("userId") val userId: String
)
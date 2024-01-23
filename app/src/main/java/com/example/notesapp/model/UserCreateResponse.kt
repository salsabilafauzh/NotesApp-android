package com.example.notesapp.model

import com.google.gson.annotations.SerializedName

data class UserCreateResponse(
//    @SerializedName("accessToken") val accessToken: String,
//    @SerializedName("refreshToken") val refreshToken: String
    @SerializedName("userId") val userId: String
)
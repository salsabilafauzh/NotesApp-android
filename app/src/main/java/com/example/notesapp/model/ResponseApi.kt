package com.example.notesapp.model

import com.google.gson.annotations.SerializedName

data class ResponseApi<T>(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data")val data: T,
)
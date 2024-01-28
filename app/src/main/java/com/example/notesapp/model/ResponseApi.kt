package com.example.notesapp.model

import com.google.gson.annotations.SerializedName

data class ResponseApi<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: T?,
)
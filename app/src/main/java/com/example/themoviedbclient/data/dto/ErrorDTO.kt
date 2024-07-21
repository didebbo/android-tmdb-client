package com.example.themoviedbclient.data.dto

import com.google.gson.annotations.SerializedName

data class ErrorDTO(
    @SerializedName("status_message")
    val statusMessage: String
)

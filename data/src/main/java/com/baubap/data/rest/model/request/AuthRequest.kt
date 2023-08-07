package com.baubap.data.rest.model.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("uuid")
    val password: String
)

package com.baubap.data.rest.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val accessToken: String,
    @SerializedName("clientInfo")
    val user: User,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("lastLoginDate")
    val lastLoginDate: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("state")
    val state: String
)

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)

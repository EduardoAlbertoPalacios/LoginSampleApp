package com.baubap.data.rest.service

import com.baubap.data.rest.model.response.AuthResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("api/v1/auth")
    suspend fun auth(
        @Query("email") email: String,
        @Query("password") password: String
    ): AuthResponse
}

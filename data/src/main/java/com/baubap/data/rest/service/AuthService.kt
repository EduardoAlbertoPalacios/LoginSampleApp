package com.baubap.data.rest.service

import com.baubap.data.rest.model.response.AuthResponse
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Auth service contract for performing login from the network.
 * defines the endpoint and the user credential for doing login.
 */
interface AuthService {
    @POST("api/v1/auth")
    suspend fun auth(
        @Query("email") email: String,
        @Query("password") password: String
    ): AuthResponse
}

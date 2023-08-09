package com.baubap.domain.mapper

import com.baubap.data.rest.model.response.AuthResponse
import com.baubap.domain.entities.AuthEntity

/**
 * Transform the [AuthResponse] to an [AuthEntity].
 *
 * AuthEntity encapsulate only the data that will be expose to the UI layer.
 */
fun AuthResponse.mapToEntity() = AuthEntity(
    user.name,
    message,
    lastLoginDate
)

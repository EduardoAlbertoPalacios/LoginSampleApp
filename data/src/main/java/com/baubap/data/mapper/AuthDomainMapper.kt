package com.baubap.domain.mapper

import com.baubap.data.rest.model.response.AuthResponse
import com.baubap.domain.entities.AuthEntity

fun AuthResponse.toDomainMapper() = AuthEntity(
    user.name,
    message,
    lastLoginDate
)

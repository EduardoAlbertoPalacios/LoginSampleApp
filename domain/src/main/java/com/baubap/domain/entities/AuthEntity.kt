package com.baubap.domain.entities

/**
 * Represent the entities after mapping the response.
 * @param name the user name.
 * @param message success authentication message.
 * @param name the last login date.
 */
data class AuthEntity(
    val name: String,
    val message: String,
    val lastLoginDate: String,
)

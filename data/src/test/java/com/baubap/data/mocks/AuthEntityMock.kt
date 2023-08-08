package com.baubap.data.mocks

import com.baubap.domain.entities.AuthEntity

fun getAuthEntity() =
    AuthEntity(
        name = "Alberto Palacios",
        message = "welcome fake",
        lastLoginDate = "2022-08-09"
    )
package com.baubap.data.mocks

import com.baubap.data.rest.model.response.AuthResponse
import com.baubap.data.rest.model.response.User

fun getAuthResponse() = AuthResponse(
    accessToken = "JzdWIiASDOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikpva",
    user = getUserResponse(),
    timestamp = "2023-07-08T10:01:20.000S",
    lastLoginDate = "2022-08-09",
    message = "welcome fake",
    status = "Active",
    state = "Registered"
)

fun getUserResponse() = User(
    23,
    "Alberto Palacios",
    "edpalaciosfake@hotmail.com"
)

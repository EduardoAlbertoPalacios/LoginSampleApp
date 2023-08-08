package com.example.loginsampleapp.mocks

import com.baubap.domain.entities.AuthEntity
import com.baubap.shared.common.ProcessResult
import java.io.IOException

fun getAuthEntityProcessResult() =
    ProcessResult.Success(
        AuthEntity(
            name = "Alberto Palacios",
            message = "welcome fake",
            lastLoginDate = "2022-08-09"
        )
    )

fun getAuthEntityProcessResultError() =
    ProcessResult.Error(IOException("Fake error"))

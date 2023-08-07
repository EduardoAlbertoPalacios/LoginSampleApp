package com.baubap.data.datasource

import com.baubap.data.rest.model.request.AuthRequest
import com.baubap.data.rest.model.response.AuthResponse
import com.baubap.domain.usecase.Params
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun executeLogin(requestParams: AuthRequest): Flow<AuthResponse>
}

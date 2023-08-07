package com.baubap.data.datasource

import com.baubap.data.rest.model.request.AuthRequest
import com.baubap.data.rest.model.response.AuthResponse
import com.baubap.data.rest.service.AuthService
import com.baubap.domain.usecase.Params
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthDataSourceImpl(
    private val authService: AuthService,
    private val dispatcher: CoroutineDispatcher
) : AuthDataSource {
    override fun executeLogin(requestParams: AuthRequest): Flow<AuthResponse> = flow {
        requestParams.apply {
            emit(authService.auth(email, password))
        }
    }.flowOn(dispatcher)
}

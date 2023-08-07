package com.baubap.data.repository

import com.baubap.data.datasource.AuthDataSource
import com.baubap.data.rest.model.request.AuthRequest
import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.mapper.toDomainMapper
import com.baubap.domain.repository.AuthRepository
import com.baubap.domain.usecase.Params
import com.baubap.shared.exceptions.ApplicationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AuthDataRepository(private val dataSource: AuthDataSource) : AuthRepository {
    override fun executeLogin(params: Params): Flow<AuthEntity> {
        val request = params.run {
            AuthRequest(email, password)
        }
        return dataSource.executeLogin(request).map {
            it.toDomainMapper()
        }.onError()
    }

    private fun <T> Flow<T>.onError(): Flow<T> = flow {
        try {
            collect {
                emit(it)
            }
        } catch (error: Exception) {
            throw ApplicationException.LoginException(error.message)
        }
    }
}

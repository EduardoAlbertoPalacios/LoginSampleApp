package com.baubap.domain.repository

import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.usecase.Params
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun executeLogin(params: Params): Flow<AuthEntity>
}
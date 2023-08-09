package com.baubap.domain.repository

import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.usecase.Params
import kotlinx.coroutines.flow.Flow

/**
 * Auth repository interface for performing login.
 */
interface AuthRepository {
    /**
     * This function Performs the user authentication.
     *
     * @param params The user credentials that will be sent to the web service.
     */
    fun executeLogin(params: Params): Flow<AuthEntity>
}

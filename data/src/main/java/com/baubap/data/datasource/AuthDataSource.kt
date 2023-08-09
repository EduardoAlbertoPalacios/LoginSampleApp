package com.baubap.data.datasource

import com.baubap.data.rest.model.request.AuthRequest
import com.baubap.data.rest.model.response.AuthResponse
import com.baubap.domain.usecase.Params
import kotlinx.coroutines.flow.Flow

/**
 * Data Source for making the login operation.
 */
interface AuthDataSource {
    /**
     * This contract expose a function to call the network client directly.
     *
     * @param requestParams Represent the user credentials for making the operation.
     */
    fun executeLogin(requestParams: AuthRequest): Flow<AuthResponse>
}

package com.baubap.domain.usecase

import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.repository.AuthRepository
import com.baubap.shared.common.ProcessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ExecuteLoginUseCase(private val repository: AuthRepository) :
    UseCase<Params, ProcessResult<AuthEntity>>() {
    override suspend fun buildUseCase(params: Params): Flow<ProcessResult<AuthEntity>> = flow {
        repository.executeLogin(params).catch {
            emit(ProcessResult.Error(it))
        }.collect {
            emit(ProcessResult.Success(it))
        }
    }
}

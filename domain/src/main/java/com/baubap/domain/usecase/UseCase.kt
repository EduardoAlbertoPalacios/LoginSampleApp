package com.baubap.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class UseCase<Params, T> {
    suspend fun execute(params: Params): Flow<T> = buildUseCase(params)

    abstract suspend fun buildUseCase(params: Params): Flow<T>
}

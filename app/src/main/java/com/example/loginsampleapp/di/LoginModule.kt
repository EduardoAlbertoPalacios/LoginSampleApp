package com.example.loginsampleapp.di

import com.baubap.data.datasource.AuthDataSource
import com.baubap.data.datasource.AuthDataSourceImpl
import com.baubap.data.repository.AuthDataRepository
import com.baubap.data.rest.service.AuthService
import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.repository.AuthRepository
import com.baubap.domain.usecase.ExecuteLoginUseCase
import com.baubap.domain.usecase.Params
import com.baubap.domain.usecase.UseCase
import com.baubap.shared.common.ProcessResult
import com.example.loginsampleapp.di.NameInstancesModule.RETROFIT_INSTANCE
import com.example.loginsampleapp.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val moduleLoginService = module {
    single<AuthService> {
        (get(named(RETROFIT_INSTANCE)) as Retrofit).create(AuthService::class.java)
    }

    single<AuthDataSource> {
        AuthDataSourceImpl(authService = get(), dispatcher = get())
    }

    single<AuthRepository> {
        AuthDataRepository(dataSource = get())
    }

    single<UseCase<Params, ProcessResult<AuthEntity>>> {
        ExecuteLoginUseCase(repository = get())
    }

    viewModel {
        LoginViewModel(useCase = get())
    }
}

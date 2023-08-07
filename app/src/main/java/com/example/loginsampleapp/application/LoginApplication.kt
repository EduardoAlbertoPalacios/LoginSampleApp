package com.example.loginsampleapp.application

import android.app.Application
import com.example.loginsampleapp.di.moduleLoginService
import com.example.loginsampleapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LoginApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LoginApplication)
            modules(
                listOf(
                    networkModule,
                    moduleLoginService
                )
            )
        }
    }
}

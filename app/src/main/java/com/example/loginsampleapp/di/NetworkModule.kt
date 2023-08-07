package com.example.loginsampleapp.di

import com.baubap.data.rest.interceptors.authInterceptor.AuthInterceptor
import com.baubap.data.rest.interceptors.networkInterceptor.CheckNetworkInterceptor
import com.example.loginsampleapp.BuildConfig
import com.example.loginsampleapp.di.NameInstancesModule.AUTH_INTERCEPTOR_INSTANCE
import com.example.loginsampleapp.di.NameInstancesModule.CHECK_NETWORK_INTERCEPTOR_INSTANCE
import com.example.loginsampleapp.di.NameInstancesModule.RETROFIT_INSTANCE
import com.example.loginsampleapp.di.NameInstancesModule.TIMES_OUT
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<Interceptor>(named(CHECK_NETWORK_INTERCEPTOR_INSTANCE)) {
        CheckNetworkInterceptor(androidContext())
    }

    single<Interceptor>(named(AUTH_INTERCEPTOR_INSTANCE)) {
        AuthInterceptor(androidContext())
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get(named(CHECK_NETWORK_INTERCEPTOR_INSTANCE)))
            .addInterceptor(interceptor = get(named(AUTH_INTERCEPTOR_INSTANCE)))
            .addLoggingInterceptor()
            .setTimeouts(TIMES_OUT)
            .build()
    }

    single<Retrofit>(named(RETROFIT_INSTANCE)) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .setupDefaultConfig(okHttpClient = get())
    }

    single { Dispatchers.IO }
}

fun OkHttpClient.Builder.addLoggingInterceptor() = apply {
    addInterceptor(
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    )
}
fun OkHttpClient.Builder.setTimeouts(timeoutSeconds: Long) = apply {
    readTimeout(timeoutSeconds, TimeUnit.SECONDS)
    writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
    connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
}

fun Retrofit.Builder.setupDefaultConfig(okHttpClient: OkHttpClient): Retrofit =
    client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

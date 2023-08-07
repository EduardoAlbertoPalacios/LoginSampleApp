package com.baubap.data.rest.interceptors.networkInterceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.baubap.data.rest.interceptors.InterceptorConstants.NO_INTERNET_CONNECTION
import com.baubap.shared.exceptions.ApplicationException
import okhttp3.Interceptor
import okhttp3.Response

class CheckNetworkInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (context.isNotConnected()) {
            throw ApplicationException.NetworkException(NO_INTERNET_CONNECTION)
        }
        return chain.proceed(chain.request())
    }

    private fun Context.isConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities)
            ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun Context.isNotConnected(): Boolean = isConnected().not()
}

package com.baubap.data.rest.interceptors.authInterceptor

import android.content.Context
import com.baubap.data.rest.interceptors.InterceptorConstants.CONTENT_TYPE
import com.baubap.data.rest.interceptors.InterceptorConstants.HEADER_NAME
import com.baubap.data.rest.interceptors.InterceptorConstants.HEADER_VALUE
import com.baubap.data.rest.interceptors.InterceptorConstants.SUCCESS_CODE
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Intercept the authentication response for mocking it.
 *
 * @param context Represent the entire application context to define a fake authentication response.
 *
 */
class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val responseBody = FakeAuthResponse(context).getResponse(request.url)

        return response
            .newBuilder()
            .code(SUCCESS_CODE)
            .protocol(Protocol.HTTP_2)
            .message(responseBody)
            .body(
                responseBody.toByteArray()
                    .toResponseBody(CONTENT_TYPE.toMediaTypeOrNull())
            )
            .addHeader(HEADER_NAME, HEADER_VALUE)
            .build()
    }
}

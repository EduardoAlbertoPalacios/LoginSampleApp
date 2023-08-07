package com.baubap.data.rest.interceptors.authInterceptor

import android.content.Context
import com.baubap.data.rest.interceptors.InterceptorConstants.INVALID_CREDENTIALS_MESSAGE
import com.baubap.shared.exceptions.ApplicationException
import okhttp3.HttpUrl

class FakeAuthResponse(private val context: Context) {
    fun getResponse(httpUrl: HttpUrl): String =
        if (httpUrl.queryParameter(EMAIL) == EMAIL_CREDENTIAL
            && httpUrl.queryParameter(PASSWORD) == PASSWORD_CREDENTIAL
        ) {
           context.assets.open(JSON_PATH)
                .bufferedReader()
                .use { it.readText() }
        } else {
            throw ApplicationException.AuthenticationException(INVALID_CREDENTIALS_MESSAGE)
        }

    companion object {
        private const val JSON_PATH = "response.json"
        private const val EMAIL_CREDENTIAL = "edpalacios@hotmail.com"
        private const val PASSWORD_CREDENTIAL = "Edu1215"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}

package com.baubap.shared.exceptions

import java.io.IOException

sealed class ApplicationException : IOException() {
    class AuthenticationException(override val message: String) : ApplicationException()
    class NetworkException(override val message: String?) : ApplicationException()
    class LoginException(override val message: String?) : ApplicationException()
}

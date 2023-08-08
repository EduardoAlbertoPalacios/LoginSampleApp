package com.baubap.data.datasource

import com.baubap.data.mocks.getAuthResponse
import com.baubap.data.rest.model.request.AuthRequest
import com.baubap.data.rest.service.AuthService
import com.baubap.data.utils.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class AuthDataSourceImplTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val api: AuthService = mockk()

    private val dispatcher = Dispatchers.IO

    private val dataSource by lazy {
        AuthDataSourceImpl(api, dispatcher)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `fetch login data success`() = runTest {
        coEvery {
            api.auth(EMAIL, PASSWORD)
        } returns getAuthResponse()

        dataSource.executeLogin(AuthRequest(EMAIL, PASSWORD)).collect {
            assert(it.user.id == ID)
            assert(it.user.email == EMAIL)
            assert(it.accessToken == ACCESS_TOKEN)
            assert(it.status == STATUS)
        }
        coVerify {
            api.auth(EMAIL, PASSWORD)
        }
    }

    @Test
    fun `fetch login data error`() = runTest {
        coEvery {
            api.auth(EMAIL, PASSWORD)
        } throws IOException(FAKE_ERROR)

        dataSource.executeLogin(
            AuthRequest(
                email = EMAIL,
                password = PASSWORD
            )
        ).catch {
            assert(it.message == FAKE_ERROR)
        }.collect()

        coVerify {
            api.auth(EMAIL, PASSWORD)
        }
    }

    companion object {
        private const val FAKE_ERROR = "Fake error"
        private const val EMAIL = "edpalaciosfake@hotmail.com"
        private const val PASSWORD = "Edu123."
        private const val ACCESS_TOKEN = "JzdWIiASDOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikpva"
        private const val STATUS = "Active"
        private const val ID = 23
    }
}

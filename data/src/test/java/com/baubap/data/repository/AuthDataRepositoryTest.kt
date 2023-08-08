package com.baubap.data.repository

import com.baubap.data.datasource.AuthDataSource
import com.baubap.data.mocks.getAuthResponse
import com.baubap.data.rest.model.request.AuthRequest
import com.baubap.data.utils.rules.CoroutinesTestRule
import com.baubap.domain.usecase.Params
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class AuthDataRepositoryTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val dataSource: AuthDataSource = mockk()

    private val repository by lazy {
        AuthDataRepository(dataSource)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `fetch data from data source and mapping it success`() = runTest {
        coEvery {
            dataSource.executeLogin(
                requestParams = AuthRequest(
                    email = EMAIL,
                    password = PASSWORD
                )
            )
        } returns flowOf(getAuthResponse())

        repository.executeLogin(
            Params(
                email = EMAIL,
                password = PASSWORD
            )
        ).collect {
            assert(it.name == NAME)
            assert(it.lastLoginDate == LAST_LOGIN_DATE)
            assert(it.message == MESSAGE)
        }

        coVerify {
            dataSource.executeLogin(
                requestParams = AuthRequest(
                    email = EMAIL,
                    password = PASSWORD
                )
            )
        }
    }

    @Test
    fun `fetch data from data source error`() = runTest {
        coEvery {
            dataSource.executeLogin(
                requestParams = AuthRequest(
                    email = EMAIL,
                    password = PASSWORD
                )
            )
        } returns flow {
            throw IOException(FAKE_ERROR)
        }

        repository.executeLogin(
            Params(
                email = EMAIL,
                password = PASSWORD
            )
        ).catch {
            assert(it.message == FAKE_ERROR)
        }.collect()

        coVerify {
            dataSource.executeLogin(
                requestParams = AuthRequest(
                    email = EMAIL,
                    password = PASSWORD
                )
            )
        }
    }

    companion object {
        private const val EMAIL = "edpalaciosfake@hotmail.com"
        private const val PASSWORD = "Edu123."
        private const val NAME = "Alberto Palacios"
        private const val MESSAGE = "welcome fake"
        private const val LAST_LOGIN_DATE = "2022-08-09"
        private const val FAKE_ERROR = "Fake error"
    }
}

package com.baubap.domain.usecase

import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.mocks.getAuthEntity
import com.baubap.domain.repository.AuthRepository
import com.baubap.domain.utils.rules.CoroutinesTestRule
import com.baubap.shared.common.ProcessResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ExecuteLoginUseCaseTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val repository: AuthRepository = mockk()

    private val useCase by lazy {
        ExecuteLoginUseCase(repository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `fetch data from repository and mapping it success`() = runTest {
        coEvery {
            repository.executeLogin(params())
        } returns flowOf(getAuthEntity())

        useCase.execute(params()).collect {
            assert(it is ProcessResult.Success<AuthEntity>)
        }

        coVerify {
            repository.executeLogin(params())
        }
    }

    @Test
    fun `fetch data from repository error`() = runTest {
        coEvery {
            repository.executeLogin(params())
        } returns flow {
            throw IOException(FAKE_ERROR)
        }

        useCase.execute(params()).collect {
            assert(it is ProcessResult.Error)
        }
    }

    private fun params() = Params(email = EMAIL, password = PASSWORD)

    companion object {
        private const val EMAIL = "edpalaciosfake@hotmail.com"
        private const val PASSWORD = "Edu123."
        private const val FAKE_ERROR = "Fake error"
    }
}

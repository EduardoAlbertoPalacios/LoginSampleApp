package com.example.loginsampleapp.viewmodel

import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.usecase.Params
import com.baubap.domain.usecase.UseCase
import com.baubap.shared.common.ProcessResult
import com.example.loginsampleapp.mocks.getAuthEntityProcessResult
import com.example.loginsampleapp.mocks.getAuthEntityProcessResultError
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogType
import com.example.loginsampleapp.ui.login.LoginViewModel
import com.example.loginsampleapp.utils.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class LoginViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val useCase: UseCase<Params, ProcessResult<AuthEntity>> = mockk()

    private val viewModel by lazy {
        LoginViewModel(useCase)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `verify email and password input are invalid`() = runTest {
        viewModel.also {
            it.updateEmail(INVALID_EMAIL)
            it.updatePassword(EMPTY)
            it.executeLogin()

            advanceUntilIdle()

            assert(it._state.value.isNotValidEmail)
            assert(it._state.value.isNotValidPassword)
        }
    }

    @Test
    fun `verify email input is invalid`() = runTest {
        viewModel.also {
            it.updateEmail(INVALID_EMAIL)
            it.updatePassword(PASSWORD)
            it.executeLogin()

            advanceUntilIdle()

            assert(it._state.value.isNotValidEmail)
            assert(!it._state.value.isNotValidPassword)
        }
    }

    @Test
    fun `verify password input is invalid`() = runTest {
        viewModel.also {
            it.updateEmail(EMAIL)
            it.updatePassword(EMPTY)
            it.executeLogin()

            advanceUntilIdle()

            assert(!it._state.value.isNotValidEmail)
            assert(it._state.value.isNotValidPassword)
        }
    }

    @Test
    fun `fetch data from usecase and mapping it success`() = runTest {
        coEvery {
            useCase.execute(params())
        } coAnswers {
            flowOf(getAuthEntityProcessResult())
        }

        viewModel.also {
            it.updateEmail(EMAIL)
            it.updatePassword(PASSWORD)
            it.executeLogin()

            advanceUntilIdle()

            assert(!it._state.value.isLoading)
            assert(it._state.value.dialogType == AlertDialogType.SUCCESS)
        }

        coVerify {
            useCase.execute(params())
        }
    }

    @Test
    fun `fetch data from usecase error`() = runTest {
        coEvery {
            useCase.execute(params())
        } coAnswers {
            flowOf(getAuthEntityProcessResultError())
        }

        viewModel.also {
            it.updateEmail(EMAIL)
            it.updatePassword(PASSWORD)
            it.executeLogin()

            advanceUntilIdle()

            assert(!it._state.value.isLoading)
            assert(it._state.value.dialogType == AlertDialogType.ERROR)
            assert(it._state.value.messageDialog == FAKE_ERROR)
        }

        coVerify {
            useCase.execute(params())
        }
    }

    private fun params() = Params(email = EMAIL, password = PASSWORD)

    companion object {
        private const val INVALID_EMAIL = "edpalaciosfake@hotmail"
        private const val EMAIL = "edpalaciosfake@hotmail.com"
        private const val PASSWORD = "Edu123."
        private const val FAKE_ERROR = "Fake error"
        private const val EMPTY = ""
    }
}

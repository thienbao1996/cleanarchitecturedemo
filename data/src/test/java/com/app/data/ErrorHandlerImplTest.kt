package com.app.data

import android.util.Log
import com.app.domain.model.ErrorEntity
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ErrorHandlerImplTest {

    private lateinit var errorHandlerImpl: ErrorHandlerImpl

    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0

        errorHandlerImpl = ErrorHandlerImpl()
    }

    @Test
    fun `verify logError handles exception`() {
        errorHandlerImpl.logError("Error", "Message", null)
        verify(exactly = 1) { Log.e(any(), any()) }
    }

    @Test
    fun `verify getError handles exception`() {
        val ioError = errorHandlerImpl.getError(IOException())
        assertEquals(ErrorEntity.Network, ioError)

        val notFoundError = errorHandlerImpl.getError(
            HttpException(
                Response.error<Any>(
                    404, "".toResponseBody(null)
                )
            )
        )
        assertEquals(ErrorEntity.NotFound, notFoundError)
    }
}
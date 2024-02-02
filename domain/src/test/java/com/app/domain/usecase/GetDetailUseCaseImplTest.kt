package com.app.domain.usecase

import com.app.domain.ErrorHandler
import com.app.domain.Repository
import com.app.domain.model.ErrorEntity
import com.app.domain.model.Program
import com.app.domain.model.Result
import com.app.domain.model.detail.Detail
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetDetailUseCaseImplTest {

    private lateinit var getDetailUseCase: GetDetailUseCase
    private lateinit var repository: Repository
    private lateinit var errorHandler: ErrorHandler
    private val validUrl = "https://static.canal-plus.net/exoplayer/api/pageUrls/9731170_50001.json"
    private val invalidUrl = "http://hodor.canalplus.pro/api/v1/mycanal/detail/34dd47d57c659b511e83c520079df1a1/pfv/1501162.json?id=1501162&params[type]=pfv&cache=780000"

    @Before
    fun setUp() {
        repository = FakeRepository()
        errorHandler = FakeErrorHandler()
        getDetailUseCase = GetDetailUseCaseImpl(repository, errorHandler)
    }

    @Test
    fun `verify getDetailUseCase handles correct response`() {
        val result = getDetailUseCase(validUrl).subscribeBy {result ->
            when(result) {
                is Result.Success -> {
                    kotlin.test.assertNotNull(result)
                }
                is Result.Error -> {

                }
            }
        }
        result.dispose()
    }

    @Test
    fun `verify getDetailUseCase handles exception`() {
        val result = getDetailUseCase(invalidUrl).subscribeBy {result ->
            when(result) {
                is Result.Success -> {

                }
                is Result.Error -> {
                    assertEquals(ErrorEntity.Unknown, result.error)
                }
            }
        }
        result.dispose()
    }
}

class FakeRepository: Repository {
    override fun getPrograms(url: String): Single<List<Program>> {
        TODO("Not yet implemented")
    }

    override fun getDetail(url: String): Single<Detail> {
        return Single.create {
            Detail(
                title = "eleifend",
                subtitle = "assueverit",
                URLImage = "http://www.bing.com/search?q=fastidii",
                summary = "urbanitas"
            )
        }
    }
}

class FakeErrorHandler: ErrorHandler {
    override fun logError(tag: String, message: String, throwable: Throwable?) {
        // do nothing
    }

    override fun getError(throwable: Throwable): ErrorEntity {
        return ErrorEntity.Unknown
    }

}